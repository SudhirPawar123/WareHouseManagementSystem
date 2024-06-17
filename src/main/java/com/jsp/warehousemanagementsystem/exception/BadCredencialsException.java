package com.jsp.warehousemanagementsystem.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class BadCredencialsException extends RuntimeException {
private String message;
}
