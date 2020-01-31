package com.rwsentosa.assignment.repository;

import org.springframework.data.repository.CrudRepository;

import com.rwsentosa.assignment.model.CurrencyExchangeRate;

public interface CurrencyExchangeRateRepository extends CrudRepository<CurrencyExchangeRate, Long> {

	public CurrencyExchangeRate findByCurrencyFromAndCurrencyTo(Long currencyFrom, Long currencyTo);
}
