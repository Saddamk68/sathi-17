package com.sathi.sim.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;

import com.sathi.sim.domain.User;
import com.sathi.sim.handler.UserValidationHandler;
import com.sathi.sim.repository.UserRepository;

@Service
@DependsOn("userRepository")
public class AuthServiceImpl implements AuthService {

	@Autowired
	private UserValidationHandler loginValidation;

	@Autowired
	private UserRepository userRepository;

	@Override
	public String showHomePage(ModelMap model, String userName, String userPassword) {
//		String pageName = "login";
//		User user = userRepo.findByUserName(userName).block();
//		if (user != null && user.getId() > 0) {
//			if (!loginValidation.isValidUserPassword(user, userPassword)) {
//				model.put("errorMessage", "Invalid password");
//			} else {
//				model.put("userName", userName);
//				model.put("userPassword", userPassword);
//				pageName = "home";
//			}
//		} else {
//			model.put("errorMessage", "User name dose not exist");
//		}
//		return pageName;
		
        String[] pageName = {"login"};
        userRepository.findByUserName(userName)
                .doOnNext(user -> {
                    if (user != null && user.getId() > 0) {
                        if (!loginValidation.isValidUserPassword(user, userPassword)) {
                            model.put("errorMessage", "Invalid password");
                        } else {
                            model.put("userName", userName);
                            model.put("userPassword", userPassword);
                            pageName[0] = "home";
                        }
                    } else {
                        model.put("errorMessage", "User name does not exist");
                    }
                })
                .subscribe();
        return pageName[0];
	}

	@Override
	public String showHomePage(ModelMap model, String userName, String userPassword, String email) {
//		String pageName = "login";
//		User user = userRepo.findByUserName(userName).block();
//		if (user != null && user.getId() > 0) {
//			model.put("errorMessage", "User name already exist");
//		} else {
//			model.put("userName", userName);
//			model.put("userPassword", userPassword);
//			model.put("email", email);
//			pageName = "home";
//		}
//		return pageName;
		
        String[] pageName = {"login"};
        userRepository.findByUserName(userName)
                .doOnNext(user -> {
                    if (user != null && user.getId() > 0) {
                        model.put("errorMessage", "User name already exists");
                    } else {
                        model.put("userName", userName);
                        model.put("userPassword", userPassword);
                        model.put("email", email);
                        pageName[0] = "home";
                    }
                })
                .subscribe();
        return pageName[0];
	}

	@Override
	public String showHomePage(User user, Model model) {
		return "home";
	}

}
