package com.sathi.sim.dto;

import java.time.LocalDate;
import java.util.Set;
import java.util.StringJoiner;

import lombok.Data;

@Data
public class StudentDTO {

	private Long studentId;

	private String fullName;
	
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

	private Long addressId;
	
	private AddressDTO address;
	
	private Set<String> subjects;

	private Boolean isActive;

	private String imageUrl;

	public String getFullName() {
		this.fullName = firstName.strip() + " " + middleName.strip() + " " + lastName.strip();
		return this.fullName;
	}
	
//	public String getFullName() {
//		StringJoiner joiner = new StringJoiner(" ");
//		if (!firstName.isBlank()) {
//			joiner.add(firstName.strip());
//		}
//		if (!middleName.isBlank()) {
//			joiner.add(middleName.strip());
//		}
//		if (!lastName.isBlank()) {
//			joiner.add(lastName.strip());
//		}
//		this.fullName = joiner.toString();
//		return this.fullName;
//	}
	
}
