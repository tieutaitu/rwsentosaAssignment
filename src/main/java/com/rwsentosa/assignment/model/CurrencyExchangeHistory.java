package com.rwsentosa.assignment.model;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class CurrencyExchangeHistory {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String customerName;
	private Long currencyFrom;
	private Long currencyTo;
	private Double exchangeRate;
	private Double amountFrom;
	private Double amountTo;
	private Timestamp transactionTime;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
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

	public Double getAmountFrom() {
		return amountFrom;
	}

	public void setAmountFrom(Double amountFrom) {
		this.amountFrom = amountFrom;
	}

	public Double getAmountTo() {
		return amountTo;
	}

	public void setAmountTo(Double amountTo) {
		this.amountTo = amountTo;
	}

	public Timestamp getTransactionTime() {
		return transactionTime;
	}

	public void setTransactionTime(Timestamp transactionTime) {
		this.transactionTime = transactionTime;
	}

}
