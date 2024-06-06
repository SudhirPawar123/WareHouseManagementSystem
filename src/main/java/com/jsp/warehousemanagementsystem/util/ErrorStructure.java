package com.jsp.warehousemanagementsystem.util;

import lombok.Getter;

@Getter
public class ErrorStructure<T> {
	private int status;
	private String errorMessage;
	private T rootCause;

	public ErrorStructure<T> setStatus(int status) {
		this.status = status;
		return this;
	}

	public ErrorStructure<T> setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
		return this;
	}

	public ErrorStructure<T> setRootCause(T rootCause) {
		this.rootCause = rootCause;
		return this;
	}
}
