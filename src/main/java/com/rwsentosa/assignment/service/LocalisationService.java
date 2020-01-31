package com.rwsentosa.assignment.service;

public interface LocalisationService {

	public String getMessage(String id);

	public String getMessage(String id, Object[] args);
}