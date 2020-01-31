package com.rwsentosa.assignment.repository;

import org.springframework.data.repository.CrudRepository;

import com.rwsentosa.assignment.model.CurrencyExchangeHistory;

public interface CurrencyExchangeHistoryRepository extends CrudRepository<CurrencyExchangeHistory, Long> {
}
