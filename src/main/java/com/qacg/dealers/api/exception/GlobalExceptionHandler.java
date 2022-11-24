package com.qacg.dealers.api.exception;

import java.util.Objects;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Edoardo Cruz
 * @version 1.0.0
 * @since 08.08.2022
 */
@Slf4j(topic = "GLOBAL_EXCEPTION_HANDLER")
@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

	public static final String TRACE = "trace";

	@Value("${reflectoring.trace:false}")
	private boolean printStackTrace;

	@Override
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		ErrorResponse errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST.value(),
				"Validation error. Check the 'errors' field for more details.");
		for (FieldError fieldError : ex.getBindingResult().getFieldErrors()) {
			errorResponse.addValidationError(fieldError.getField(), fieldError.getDefaultMessage());
		}
		return ResponseEntity.badRequest().body(errorResponse);
	}

	@ExceptionHandler(BadCredentialsException.class)
	@ResponseStatus(HttpStatus.UNAUTHORIZED)
	public ResponseEntity<Object> badCredentialsException(BadCredentialsException badCredentialsException,
			WebRequest request) {
		log.error("Invalid credentials", badCredentialsException);
		return buildErrorResponse(badCredentialsException,"Invalid credentials", HttpStatus.UNAUTHORIZED, request);
	}
	
	@ExceptionHandler(AccessDeniedException.class)
	@ResponseStatus(HttpStatus.UNAUTHORIZED)
	public ResponseEntity<Object> accessDeniedException(AccessDeniedException accessDeniedException,
			WebRequest request) {
		log.error("Access denied", accessDeniedException);
		return buildErrorResponse(accessDeniedException,"Access denied", HttpStatus.UNAUTHORIZED, request);
	}
	
	
	@ExceptionHandler(NoSuchElementFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ResponseEntity<Object> handleNoSuchElementFoundException(NoSuchElementFoundException itemNotFoundException,
			WebRequest request) {
		log.error("The requested item could not be found", itemNotFoundException);
		return buildErrorResponse(itemNotFoundException, HttpStatus.NOT_FOUND, request);
	}

	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public ResponseEntity<Object> handleAllUncaughtException(Exception exception, WebRequest request) {
		log.error("An unknown error occurred", exception);
		return buildErrorResponse(exception, "An unknown error occurred, contact your administrator", HttpStatus.INTERNAL_SERVER_ERROR, request);
	}
	
	@ExceptionHandler(BusinessException.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public ResponseEntity<Object> handleAllBusinessException(BusinessException businessException, WebRequest request) {
		return buildErrorResponse(businessException, businessException.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, request);
	}

	private ResponseEntity<Object> buildErrorResponse(Exception exception, HttpStatus httpStatus, WebRequest request) {
		return buildErrorResponse(exception, exception.getMessage(), httpStatus, request);
	}

	private ResponseEntity<Object> buildErrorResponse(Exception exception, String message, HttpStatus httpStatus,
			WebRequest request) {
		ErrorResponse errorResponse = new ErrorResponse(httpStatus.value(), message);
		if (printStackTrace && isTraceOn(request)) {
			errorResponse.setStackTrace(ExceptionUtils.getStackTrace(exception));
		}
		return ResponseEntity.status(httpStatus).body(errorResponse);
	}

	private boolean isTraceOn(WebRequest request) {
		String[] value = request.getParameterValues(TRACE);
		return Objects.nonNull(value) && value.length > 0 && value[0].contentEquals("true");
	}

	@Override
	public ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers,
			HttpStatus status, WebRequest request) {

		return buildErrorResponse(ex, status, request);
	}
}