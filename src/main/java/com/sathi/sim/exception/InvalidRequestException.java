package com.sathi.sim.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class InvalidRequestException extends Exception {

	private static final long serialVersionUID = 559617845702231837L;

	public InvalidRequestException(String message) {
		super(message);
	}

}
