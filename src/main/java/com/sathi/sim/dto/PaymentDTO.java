package com.sathi.sim.dto;

import java.time.LocalDate;

import lombok.Data;

@Data
public class PaymentDTO {

	private Long id;

	private Long studentId;

	private Double amount;

	private LocalDate date;
	
	private String paymentMethod;	
	
}
