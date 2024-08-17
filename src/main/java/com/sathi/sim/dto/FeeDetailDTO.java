package com.sathi.sim.dto;

import java.util.Date;

import lombok.Data;

@Data
public class FeeDetailDTO {

	private Long id;

	private Long studentId;

	private Double totalFee;

	private Double depositFeeAmt;

	private Date depositFeeDate;

	private Double remainingFeeAmt;

	private Date remainingFeeDate;
	
}
