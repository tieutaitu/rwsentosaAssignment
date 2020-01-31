package com.rwsentosa.assignment.values;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

public class ResponseVO {
	@JsonInclude(Include.NON_NULL)
	private List<String> errorList;

	@JsonInclude(Include.NON_NULL)
	private Object data;

	@JsonInclude(Include.NON_NULL)
	private String message;

	public List<String> getErrorList() {
		return errorList;
	}

	public void setErrorList(List<String> errorList) {
		this.errorList = errorList;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}
}
