package com.sathi.sim.handler;

import org.springframework.stereotype.Component;

import com.sathi.sim.domain.User;

@Component
public class UserValidationHandler {

	public boolean isValidUserPassword(User user, String userPassword) {
		return user.getUserPassword().equals(userPassword);
	}
	
}
