package com.sathi.sim.service;

import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;

import com.sathi.sim.domain.User;

public interface AuthService {

	public String showHomePage(ModelMap model, String userName, String userPassword);

	public String showHomePage(ModelMap model, String userName, String userPassword, String email);
	
//	public String showHomePage(User user, BindingResult errors, Model model);
	
	public String showHomePage(User user, Model model);
	
}
