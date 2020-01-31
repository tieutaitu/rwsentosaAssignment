package com.rwsentosa.assignment.values;

public class ExchangeMoneyDisplayVO {
	private Long currencyFrom;
	private Long currencyTo;
	private Double exchangeRate;
	private Double amount;

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

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}
}
