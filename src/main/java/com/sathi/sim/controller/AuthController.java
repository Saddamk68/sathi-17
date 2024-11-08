package com.sathi.sim.controller;

import jakarta.validation.constraints.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sathi.sim.domain.User;
import com.sathi.sim.service.AuthService;
import com.sathi.sim.util.RegxPattern;

@Controller
@RequestMapping("/auth")
@Validated
public class AuthController {

	private AuthService authService;

	@Autowired	
	AuthController(AuthService authService) {
		this.authService = authService;
	}
	
	@GetMapping(value = "/signup")
	public String showSignUpPage(ModelMap model) {
		model.addAttribute("user", new User());
		return "LoginAndSignup";
	}

	@PostMapping(value = "/signup")
	public String showHomePage(ModelMap model, @RequestParam String userName,
			@Pattern(regexp = RegxPattern.EMAIL_ADDRESS_PATTERN, message = "Email Address is not valid") @RequestParam String userPassword,
			@RequestParam String email) {
		return authService.showHomePage(model, userName, userPassword, email);
	}

	@GetMapping(value = "/login")
	public String showAnimeLoginAndSignupPage(Model model) {
		model.addAttribute("user", new User());
		return "LoginAndSignup";
	}

	@GetMapping(value = "/logout")
	public String showLogoutPage(Model model) {
		model.addAttribute("user", new User());
		return "redirect:/auth/login";
	}
	
//	@PostMapping(value = "/login")
//	public ResponseEntity<String> showHomePage(ModelMap model, @RequestParam String userName, @RequestParam String userPassword) {
//		String response = loginService.showHomePage(model, userName, userPassword);
//		return new ResponseEntity<>(response, HttpStatus.OK);
//	}

	@PostMapping(value = "/login")
	public String showHomePage(@ModelAttribute User user, Model model) {
		return authService.showHomePage(user, model);
	}
	
}
