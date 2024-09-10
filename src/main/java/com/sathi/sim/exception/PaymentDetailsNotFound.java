package com.sathi.sim.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class PaymentDetailsNotFound extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5929939258493097731L;

	public PaymentDetailsNotFound(String message) {
		super(message);
	}

}
