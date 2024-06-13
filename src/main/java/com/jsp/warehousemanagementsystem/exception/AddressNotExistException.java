package com.jsp.warehousemanagementsystem.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class AddressNotExistException extends RuntimeException {
    private String message;
}