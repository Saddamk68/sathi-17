package com.sathi.sim.validat;

import org.apache.commons.lang3.StringUtils;

import com.sathi.sim.domain.Student;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ValidateStudentDetails {

	public static String errMsg = "";
	
	public static Boolean validateStudentDetails(Student studentDetail) {
		if (!CommonValidation.validateName(studentDetail.getFirstName())) {
			errMsg = String.format("Please enter valid first name : %s", studentDetail.getFirstName());
			log.error(errMsg);
			return false;
		}
		if (!CommonValidation.validateName(studentDetail.getLastName())) {
			errMsg = String.format("Please enter valid last name : %s", studentDetail.getLastName());
			log.error(errMsg);
			return false;
		}
		if (!StringUtils.isBlank(studentDetail.getMiddleName())
				&& !CommonValidation.validateName(studentDetail.getMiddleName())) {
			errMsg = String.format("Please enter valid middle name : %s", studentDetail.getMiddleName());
			log.error(errMsg);
			return false;
		}
		if (!CommonValidation.validateMobileNumber(studentDetail.getContactNum())) {
			errMsg = String.format("Please enter valid contact number : %s", studentDetail.getContactNum());
			log.error(errMsg);
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
