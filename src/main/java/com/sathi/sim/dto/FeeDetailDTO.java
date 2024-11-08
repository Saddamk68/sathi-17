package com.sathi.sim.dto;

import java.time.LocalDate;

import lombok.Data;

@Data
public class FeeDetailDTO {

	private Long id;

	private Long studentId;

	private Double totalFee;

	private Double depositFeeAmt;

	private LocalDate depositFeeDate;

	private Double remainingFeeAmt;

	private LocalDate remainingFeeDate;
	
}
