package com.sathi.sim.dto;

import java.util.Date;

import lombok.Data;

@Data
public class StudentDTO {

	private Long studentId;
	
	private String firstName;

	private String middleName;

	private String lastName;
	
	private String fatherName;

	private String motherName;

	private Date dob;

	private String gender;

	private String schoolName;
	
	private Integer className;
	
	private String contactNum;

	private String email;

	private String imageUrl;
	
}
