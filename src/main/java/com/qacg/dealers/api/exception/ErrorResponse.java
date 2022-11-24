package com.qacg.dealers.api.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author Edoardo Cruz
 * @version 1.0.0
 * @since 08.08.2022
 */
@Getter
@Setter
@RequiredArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorResponse implements Serializable {
	
	private static final long serialVersionUID = 577567012576773824L;
	
	private final int status;
	
	private final String message;
	
	private String stackTrace;
	
	private transient List<ValidationError> errors;

	@Getter
	@Setter
	@RequiredArgsConstructor
	private static class ValidationError {
		private final String field;
		private final String message;
	}

	public void addValidationError(String field, String message) {
		if (Objects.isNull(errors)) {
			errors = new ArrayList<>();
		}
		errors.add(new ValidationError(field, message));
	}
}