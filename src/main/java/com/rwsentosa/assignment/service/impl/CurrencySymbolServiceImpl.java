package com.rwsentosa.assignment.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.rwsentosa.assignment.model.CurrencySymbol;
import com.rwsentosa.assignment.repository.CurrencySymbolRepository;
import com.rwsentosa.assignment.service.CurrencySymbolService;

@Component
public class CurrencySymbolServiceImpl implements CurrencySymbolService {

	@Autowired
	private CurrencySymbolRepository currencySymbolRepository;

	@Override
	public CurrencySymbol findBySymbol(String symbol) {
		return currencySymbolRepository.findBySymbol(symbol);
	}
}
