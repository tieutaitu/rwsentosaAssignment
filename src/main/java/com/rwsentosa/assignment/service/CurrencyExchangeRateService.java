package com.rwsentosa.assignment.service;

import com.rwsentosa.assignment.model.CurrencyExchangeRate;

public interface CurrencyExchangeRateService {

	public CurrencyExchangeRate findByCurrencyFromAndCurrencyTo(Long currencyFrom, Long currencyTo);
}
