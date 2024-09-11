package com.sathi.sim.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class AddressDetailsNotFound extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5547189273703140806L;

	public AddressDetailsNotFound(String message) {
		super(message);
	}

}
