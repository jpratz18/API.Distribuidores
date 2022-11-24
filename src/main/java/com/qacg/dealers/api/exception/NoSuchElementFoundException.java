package com.qacg.dealers.api.exception;

/**
 * @author Edoardo Cruz
 * @version 1.0.0
 * @since 08.08.2022
 */
public class NoSuchElementFoundException extends RuntimeException {
	
	private static final long serialVersionUID = 422580545031189574L;

	public NoSuchElementFoundException(String message){
        super(message);
    }
	
}