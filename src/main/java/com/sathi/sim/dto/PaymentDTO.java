package com.sathi.sim.dto;

import java.util.Date;

import lombok.Data;

@Data
public class PaymentDTO {

	private Long id;

	private Long studentId;

	private Double amount;

	private Date date;

	private Double remainingAmt;

	private Date remainingAmtDate;

	private String paymentMethod;	
	
}
