package com.jsp.warehousemanagementsystem.util;

import lombok.Getter;

@Getter
public class SimpleResponseStructure<T> {
private int status;
private String message;

public SimpleResponseStructure<T> setStatus(int status) {
	this.status = status;
	return this;
}
public SimpleResponseStructure<T> setMessage(String message) {
	this.message = message;
	return this;
}

}
