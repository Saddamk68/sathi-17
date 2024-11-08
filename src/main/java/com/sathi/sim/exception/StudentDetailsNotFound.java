package com.sathi.sim.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class StudentDetailsNotFound extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 919437613663951784L;

	public StudentDetailsNotFound(String message) {
		super(message);
	}

}
