package com.rwsentosa.assignment.service.impl;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

import com.rwsentosa.assignment.service.LocalisationService;

@Component
public class LocalisationServiceImpl implements LocalisationService {

	@Autowired
	private MessageSource messageSource;

	@Override
	public String getMessage(String id, Object[] args) {
		Locale locale = LocaleContextHolder.getLocale();
		try {
			return messageSource.getMessage(id, args, locale);
		} catch (NoSuchMessageException e) {
			return messageSource.getMessage(id, args, Locale.US);
		}
	}

	@Override
	public String getMessage(String id) {
		return getMessage(id, null);
	}
}
