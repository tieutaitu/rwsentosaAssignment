package com.rwsentosa.assignment;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.rwsentosa.assignment.controller.MoneyChangerController;
import com.rwsentosa.assignment.model.CurrencyExchangeRate;
import com.rwsentosa.assignment.model.CurrencySymbol;
import com.rwsentosa.assignment.service.CurrencyExchangeRateService;
import com.rwsentosa.assignment.service.CurrencySymbolService;
import com.rwsentosa.assignment.service.LocalisationService;
import com.rwsentosa.assignment.values.ExchangeMoneyDisplayVO;
import com.rwsentosa.assignment.values.ResponseVO;

@SpringBootTest
public class MoneyChangerControllerTest {

	@InjectMocks
	private MoneyChangerController moneyChangerController;

	@Mock
	private CurrencySymbolService currencySymbolService;

	@Mock
	private LocalisationService localisationService;

	@Mock
	private CurrencyExchangeRateService currencyExchangeRateService;

	@Test
	public void testShowExchangeMoneyShouldReturnOK() {
		CurrencySymbol currencyFrom = new CurrencySymbol();
		currencyFrom.setId(1L);
		currencyFrom.setSymbol("SGD");
		currencyFrom.setRoundingNumber(1);

		CurrencySymbol currencyTo = new CurrencySymbol();
		currencyTo.setId(2L);
		currencyTo.setSymbol("US");
		currencyTo.setRoundingNumber(2);
		
		CurrencyExchangeRate currencyExchangeRate = new CurrencyExchangeRate();
		currencyExchangeRate.setCurrencyFrom(currencyFrom.getId());
		currencyExchangeRate.setCurrencyTo(currencyTo.getId());
		currencyExchangeRate.setExchangeRate(0.742164);

		when(currencySymbolService.findBySymbol("SGD")).thenReturn(currencyFrom);
		when(currencySymbolService.findBySymbol("USD")).thenReturn(currencyTo);
		when(currencyExchangeRateService.findByCurrencyFromAndCurrencyTo(currencyFrom.getId(),
					currencyTo.getId())).thenReturn(currencyExchangeRate);

		ResponseVO response = moneyChangerController.showExchangeMoney("SGD", "USD", 19000.0);
		assertNull(response.getErrorList());
		assertNotNull(response.getData());
		ExchangeMoneyDisplayVO exchangeMoneyDisplayVO = (ExchangeMoneyDisplayVO) response.getData();
		assertEquals(0.742164, exchangeMoneyDisplayVO.getExchangeRate());
		assertEquals(14101.12, exchangeMoneyDisplayVO.getAmount());
	}

	@Test
	public void testShowExchangeMoneyShouldReturnErrorForInvalidCurrencyFrom() {
		CurrencySymbol currencyTo = new CurrencySymbol();
		currencyTo.setId(2L);
		currencyTo.setSymbol("SGD");

		when(currencySymbolService.findBySymbol("VND")).thenReturn(null);
		when(currencySymbolService.findBySymbol("SGD")).thenReturn(currencyTo);
		when(localisationService.getMessage("moneyExchange.currencyFromNotFound"))
				.thenReturn("Currency From Not Found");

		ResponseVO response = moneyChangerController.showExchangeMoney("VND", "SGD", 10000.0);
		assertNotNull(response.getErrorList());
		assertEquals(1, response.getErrorList().size());
		assertEquals("Currency From Not Found", response.getErrorList().get(0));
	}
	
	@Test
	public void testShowExchangeMoneyShouldReturnErrorForInvalidCurrencyTo() {
		CurrencySymbol currencyFrom = new CurrencySymbol();
		currencyFrom.setId(1L);
		currencyFrom.setSymbol("SGD");

		when(currencySymbolService.findBySymbol("VND")).thenReturn(null);
		when(currencySymbolService.findBySymbol("SGD")).thenReturn(currencyFrom);
		when(localisationService.getMessage("moneyExchange.currencyToNotFound"))
				.thenReturn("Currency To Not Found");

		ResponseVO response = moneyChangerController.showExchangeMoney("SGD", "VND", 10000.0);
		assertNotNull(response.getErrorList());
		assertEquals(1, response.getErrorList().size());
		assertEquals("Currency To Not Found", response.getErrorList().get(0));
	}
}
