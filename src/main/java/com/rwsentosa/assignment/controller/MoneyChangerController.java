package com.rwsentosa.assignment.controller;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.math3.util.Precision;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.rwsentosa.assignment.model.CurrencyExchangeHistory;
import com.rwsentosa.assignment.model.CurrencyExchangeRate;
import com.rwsentosa.assignment.model.CurrencySymbol;
import com.rwsentosa.assignment.service.CurrencyExchangeHistoryService;
import com.rwsentosa.assignment.service.CurrencyExchangeRateService;
import com.rwsentosa.assignment.service.CurrencySymbolService;
import com.rwsentosa.assignment.service.LocalisationService;
import com.rwsentosa.assignment.values.ExchangeMoneyDisplayVO;
import com.rwsentosa.assignment.values.ResponseVO;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/money-changer")
public class MoneyChangerController {

	@Autowired
	private LocalisationService localisationService;

	@Autowired
	private CurrencySymbolService currencySymbolService;

	@Autowired
	private CurrencyExchangeRateService currencyExchangeRateService;
	
	@Autowired
	private CurrencyExchangeHistoryService currencyExchangeHistoryService;

	@ApiOperation(value = "Show money exchange information")
	@GetMapping("/show")
	@ResponseBody
	public ResponseVO showExchangeMoney(@RequestParam(value = "from") String from,
			@RequestParam(value = "to") String to, @RequestParam(value = "amount") Double amount) {

		// Validate input variables
		List<String> errorList = new ArrayList<>();
		CurrencySymbol currencyFrom = currencySymbolService.findBySymbol(from);
		CurrencySymbol currencyTo = currencySymbolService.findBySymbol(to);
		CurrencyExchangeRate currencyExchangeRate = null;
		String message = "";
		if (currencyFrom == null) {
			message = localisationService.getMessage("moneyExchange.currencyFromNotFound");
			errorList.add(message);
		}
		if (currencyTo == null) {
			message = localisationService.getMessage("moneyExchange.currencyToNotFound");
			errorList.add(message);
		}
		if (currencyFrom != null && currencyTo != null) {
			currencyExchangeRate = currencyExchangeRateService.findByCurrencyFromAndCurrencyTo(currencyFrom.getId(),
					currencyTo.getId());
			if (currencyExchangeRate == null) {
				message = localisationService.getMessage("moneyExchange.noExchangeValue");
				errorList.add(message);
			}
		}
		if (amount < 0) {
			message = localisationService.getMessage("moneyExchange.amountShouldBePositive");
			errorList.add(message);
		}

		// Do request
		ResponseVO response = new ResponseVO();
		if (CollectionUtils.isEmpty(errorList)) {
			ExchangeMoneyDisplayVO exchangeMoneyDisplayVO = new ExchangeMoneyDisplayVO();
			exchangeMoneyDisplayVO.setCurrencyFrom(currencyFrom.getId());
			exchangeMoneyDisplayVO.setCurrencyTo(currencyTo.getId());
			exchangeMoneyDisplayVO.setExchangeRate(currencyExchangeRate.getExchangeRate());
			exchangeMoneyDisplayVO.setAmount(Precision.round(amount * currencyExchangeRate.getExchangeRate(), currencyTo.getRoundingNumber()));

			response.setData(exchangeMoneyDisplayVO);
		} else {
			response.setErrorList(errorList);
		}

		return response;
	}

	@ApiOperation(value = "Process transaction after receiving client's approval")
	@GetMapping("/process-transaction")
	@ResponseBody
	public ResponseVO processTransaction(@RequestParam(value = "from") String from,
			@RequestParam(value = "to") String to, @RequestParam(value = "amount") Double amount,
			@RequestParam(value = "customerName") String customerName) {

		ResponseVO response = showExchangeMoney(from, to, amount);

		if (CollectionUtils.isEmpty(response.getErrorList())) {
			// Save transaction
			ExchangeMoneyDisplayVO exchangeMoneyDisplayVO = (ExchangeMoneyDisplayVO) response.getData();
			CurrencyExchangeHistory currencyExchangeHistory = new CurrencyExchangeHistory();
			currencyExchangeHistory.setCustomerName(customerName);
			currencyExchangeHistory.setCurrencyFrom(exchangeMoneyDisplayVO.getCurrencyFrom());
			currencyExchangeHistory.setCurrencyTo(exchangeMoneyDisplayVO.getCurrencyTo());
			currencyExchangeHistory.setExchangeRate(exchangeMoneyDisplayVO.getExchangeRate());
			currencyExchangeHistory.setAmountFrom(amount);
			currencyExchangeHistory.setAmountTo(exchangeMoneyDisplayVO.getAmount());
			currencyExchangeHistory.setTransactionTime(new Timestamp(System.currentTimeMillis()));

			currencyExchangeHistoryService.saveExchangeHistory(currencyExchangeHistory);
			String successMessage = localisationService.getMessage("moneyExchange.transactionHasBeenSavedSuccessfully");
			response = new ResponseVO();
			response.setMessage(successMessage);
		}

		return response;
	}
}
