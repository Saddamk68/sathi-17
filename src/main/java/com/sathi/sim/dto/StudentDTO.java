package com.sathi.sim.dto;

import java.time.LocalDate;
import java.util.Set;

import lombok.Data;

@Data
public class StudentDTO {

	private Long studentId;
	
	private String firstName;

	private String middleName;

	private String lastName;
	
	private String fatherName;

	private String motherName;

	private LocalDate dob;

	private String gender;

	private String schoolName;
	
	private Integer className;
	
	private String contactNum;

	private String email;

	private AddressDTO address;
	
	private Set<String> subjects;

	private Boolean isActive;

	private String imageUrl;

//	public String getFullName() {
//		return firstName.strip() + " " + middleName.strip() + " " + lastName.strip();
//	}
	
}
