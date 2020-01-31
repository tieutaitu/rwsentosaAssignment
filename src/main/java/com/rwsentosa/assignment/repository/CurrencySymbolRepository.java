package com.rwsentosa.assignment.repository;

import org.springframework.data.repository.CrudRepository;

import com.rwsentosa.assignment.model.CurrencySymbol;

public interface CurrencySymbolRepository extends CrudRepository<CurrencySymbol, Long> {

	public CurrencySymbol findBySymbol(String symbol);
}
