package com.rwsentosa.assignment.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.rwsentosa.assignment.model.CurrencyExchangeRate;
import com.rwsentosa.assignment.repository.CurrencyExchangeRateRepository;
import com.rwsentosa.assignment.service.CurrencyExchangeRateService;

@Component
public class CurrencyExchangeRateServiceImpl implements CurrencyExchangeRateService {

	@Autowired
	private CurrencyExchangeRateRepository currencyExchangeRepository;

	@Override
	public CurrencyExchangeRate findByCurrencyFromAndCurrencyTo(Long currencyFrom, Long currencyTo) {
		return currencyExchangeRepository.findByCurrencyFromAndCurrencyTo(currencyFrom, currencyTo);
	}

}
