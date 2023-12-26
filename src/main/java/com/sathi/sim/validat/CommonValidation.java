package com.sathi.sim.validat;

import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

import com.sathi.sim.util.RegxPattern;

public class CommonValidation {

	public static Boolean validateName(String studentName) {
		if (!StringUtils.isBlank(studentName)) {
			return Pattern.matches(RegxPattern.ONLY_ALPHABET_PATTERN, studentName);
		}
		return false;
	}

	public static Boolean validateEmailId(String emailId) {
		if (!StringUtils.isBlank(emailId)) {
			return Pattern.matches(RegxPattern.EMAIL_ADDRESS_PATTERN, emailId);
		}
		return false;
	}

	public static Boolean validateMobileNumber(String mobileNumber) {
		if (!StringUtils.isBlank(mobileNumber) && mobileNumber.length() == 10) {
			return Pattern.matches(RegxPattern.PHONE_NUMBER_PATTERN, mobileNumber);
		}
		return false;
	}

}
