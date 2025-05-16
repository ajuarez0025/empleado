package com.siscon.crud.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.siscon.crud.dto.ErrorDTO;

import io.swagger.v3.oas.annotations.Hidden;

/**
 * The Class GlobalExceptionHandler.
 * 
 * @author ajuarez
 */
@Hidden
@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler{

	
	/**
	 * Handle method argument not valid.
	 *
	 * @param ex the ex
	 * @param headers the headers
	 * @param status the status
	 * @param request the request
	 * @return the response entity
	 */
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(
			MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
		StringBuilder errors = new StringBuilder();
		ex.getBindingResult().getAllErrors().forEach((error) -> {			
			String value = error.getDefaultMessage();			
			errors.append(value);
                        
		});
		ErrorDTO response = new ErrorDTO();
		response.setMessage(errors.toString());
		response.setStatus(HttpStatus.BAD_REQUEST.value());
		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	}
	
	/**
	 * Handle service exception.
	 *
	 * @param exception the exception
	 * @return the response entity
	 */
	@ExceptionHandler(ServiceException.class)
	public ResponseEntity<Object> handleServiceException(ServiceException exception) {		
		ErrorDTO response = new ErrorDTO();
		response.setMessage(exception.getMessage());
		response.setStatus(exception.getStatusCode().value());
		return new ResponseEntity<>(response, exception.getStatusCode());
	}
}
