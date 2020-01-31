package com.rwsentosa.assignment.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.rwsentosa.assignment.model.CurrencyExchangeHistory;
import com.rwsentosa.assignment.repository.CurrencyExchangeHistoryRepository;
import com.rwsentosa.assignment.service.CurrencyExchangeHistoryService;

@Component
public class CurrencyExchangeHistoryServiceImpl implements CurrencyExchangeHistoryService {

	@Autowired
	private CurrencyExchangeHistoryRepository currencyExchangeHistoryRepository;

	@Override
	public void saveExchangeHistory(CurrencyExchangeHistory entity) {
		currencyExchangeHistoryRepository.save(entity);
	}
}
