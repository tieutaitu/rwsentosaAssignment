package com.rwsentosa.assignment.service;

import com.rwsentosa.assignment.model.CurrencySymbol;

public interface CurrencySymbolService {

	public CurrencySymbol findBySymbol(String symbol);
}
