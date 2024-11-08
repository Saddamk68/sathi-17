package com.sathi.sim.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class SubjectDetailsNotFound extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 169035815475150616L;

	public SubjectDetailsNotFound(String message) {
		super(message);
	}

}
