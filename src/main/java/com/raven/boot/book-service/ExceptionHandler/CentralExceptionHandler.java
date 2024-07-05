package com.REST_API_book.java.ExceptionHandler;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import jakarta.validation.ConstraintViolationException;

@RestControllerAdvice
public class CentralExceptionHandler extends ResponseEntityExceptionHandler{

	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		List<String> errors= ex.getBindingResult().getFieldErrors().stream().map(e -> e.getDefaultMessage()).collect(Collectors.toList());
		//This process gets the full stacktrace and maps it's message to a List
		System.out.println("Binding Error in Handler");
		return new ResponseEntity<Object>(errors, status);
		
	}

	@SuppressWarnings("rawtypes")
	@ExceptionHandler(jakarta.validation.ConstraintViolationException.class)
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	
	public ResponseEntity handleConstraintViolationException(ConstraintViolationException ex){
		
		System.out.println("Indside ConstraintViolationExceptionHandler");
		List<String> message = ex.getConstraintViolations().stream().map(e -> e.getMessage()).collect(Collectors.toList());
		ex.printStackTrace();
		return ResponseEntity.of(Optional.of(message));
	}
	
}
