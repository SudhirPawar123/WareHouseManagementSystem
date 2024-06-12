package com.jsp.warehousemanagementsystem.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class EmailAlreadyExistException extends RuntimeException {
private String message;
}
