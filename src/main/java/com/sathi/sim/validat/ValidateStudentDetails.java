package com.sathi.sim.validat;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sathi.sim.domain.Student;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ValidateStudentDetails {

	private static final Logger LOGGER = LoggerFactory.getLogger(ValidateStudentDetails.class);

	public static String errMsg = "";
	
	public static Boolean validateStudentDetails(Student studentDetail) {
		if (!CommonValidation.validateName(studentDetail.getFirstName())) {
			errMsg = String.format("Please enter valid first name : %s", studentDetail.getFirstName());
			LOGGER.error(errMsg);
			return false;
		}
		if (!CommonValidation.validateName(studentDetail.getLastName())) {
			errMsg = String.format("Please enter valid last name : %s", studentDetail.getLastName());
			LOGGER.error(errMsg);
			return false;
		}
		if (!StringUtils.isBlank(studentDetail.getMiddleName())
				&& !CommonValidation.validateName(studentDetail.getMiddleName())) {
			errMsg = String.format("Please enter valid middle name : %s", studentDetail.getMiddleName());
			LOGGER.error(errMsg);
			return false;
		}
		if (!CommonValidation.validateMobileNumber(studentDetail.getContactNum())) {
			errMsg = String.format("Please enter valid contact number : %s", studentDetail.getContactNum());
			LOGGER.error(errMsg);
			return false;
		}
		if (StringUtils.isBlank(studentDetail.getImageUrl())) {
			if ("male".equalsIgnoreCase(studentDetail.getGender())) 
				studentDetail.setImageUrl("man.png");
			else 
				studentDetail.setImageUrl("woman.png");
		}
		return true;
	}
	
}
