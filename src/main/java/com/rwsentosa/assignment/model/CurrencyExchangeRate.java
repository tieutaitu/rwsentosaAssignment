package com.rwsentosa.assignment.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class CurrencyExchangeRate {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private Long currencyFrom;
	private Long currencyTo;
	private Double exchangeRate;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getCurrencyFrom() {
		return currencyFrom;
	}

	public void setCurrencyFrom(Long currencyFrom) {
		this.currencyFrom = currencyFrom;
	}

	public Long getCurrencyTo() {
		return currencyTo;
	}

	public void setCurrencyTo(Long currencyTo) {
		this.currencyTo = currencyTo;
	}

	public Double getExchangeRate() {
		return exchangeRate;
	}

	public void setExchangeRate(Double exchangeRate) {
		this.exchangeRate = exchangeRate;
	}
}
