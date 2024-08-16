package com.sathi.sim.dto;

import lombok.Data;

@Data
public class AddressDTO {

	private Long id;

	private String firstLine;

	private String secondLine;

	private String state;

	private String city;

	private String pinCode;

}
