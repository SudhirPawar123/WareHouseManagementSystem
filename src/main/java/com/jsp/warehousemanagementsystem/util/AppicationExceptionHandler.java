package com.jsp.warehousemanagementsystem.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.jsp.warehousemanagementsystem.exception.AdminNotFoundException;
import com.jsp.warehousemanagementsystem.exception.EmailAlreadyExistException;
import com.jsp.warehousemanagementsystem.exception.IllegalOperationException;
import com.jsp.warehousemanagementsystem.exception.WareHouseNotFoundByIdException;

@RestControllerAdvice
public class AppicationExceptionHandler {

	private ResponseEntity<ErrorStructure<String>> errorResponse(HttpStatus status,String message,String rootCause){
		return ResponseEntity
				.status(status)
				.body(new ErrorStructure<String>()
				.setStatus(status.value())
				.setErrorMessage(message)
				.setRootCause(rootCause));
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ErrorStructure<Map<String,String>>> handleMethodArgumentNotValid(MethodArgumentNotValidException ex){
		List<ObjectError> errors=ex.getAllErrors();
		Map<String,String> allErrors=new HashMap<>();
				errors.forEach(error ->{
					FieldError fieldError=(FieldError)error;
					String field=fieldError.getField();
					String message=fieldError.getDefaultMessage();
					allErrors.put(field, message);
				});
		
		
		return ResponseEntity
				.status(HttpStatus.BAD_REQUEST)
				.body(new ErrorStructure<Map<String,String>>()
				.setStatus(HttpStatus.BAD_REQUEST.value())
				.setErrorMessage("Invalid input")
				.setRootCause(allErrors));
	//
	}
	

	@ExceptionHandler(IllegalOperationException.class)
	public ResponseEntity<ErrorStructure<String>> handleIllegalOperation(IllegalOperationException ex) {
		return errorResponse(HttpStatus.NOT_FOUND, ex.getMessage(), "The admin is not found by the given id");
	}
	
	@ExceptionHandler(WareHouseNotFoundByIdException.class)
	public ResponseEntity<ErrorStructure<String>> handleWareHouseNotFound(WareHouseNotFoundByIdException ex) {
		return errorResponse(HttpStatus.NOT_FOUND, ex.getMessage(), "The warehouse is not found ");
	}
	
	@ExceptionHandler(AdminNotFoundException.class)
	public ResponseEntity<ErrorStructure<String>> handleAdminNotFound(AdminNotFoundException ex) {
		return errorResponse(HttpStatus.NOT_FOUND, ex.getMessage(), "The admin is not found ");
	}
	
	
	@ExceptionHandler(EmailAlreadyExistException.class)
	public ResponseEntity<ErrorStructure<String>> handleAdminNotFound(EmailAlreadyExistException ex) {
		return errorResponse(HttpStatus.NOT_FOUND, ex.getMessage(), "The email is aleready exist,please enter different email ");
	}
}
