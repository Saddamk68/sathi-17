package com.sathi.sim.service;

import com.sathi.sim.domain.FeeDetail;
import com.sathi.sim.dto.FeeDetailDTO;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface FeeDetailService {

	Mono<FeeDetailDTO> createFeeDetail(FeeDetail feeDetail);

	Mono<FeeDetailDTO> getFeeDetailByStudentId(Long studentId);
	
	Flux<FeeDetailDTO> getFeeDetailByRemainingFeeAmtMoreThan(Double remainingFeeAmt);

	Flux<FeeDetailDTO> getFeeDetailByRemainingFeeDateLessThan(String remainingFeeAmtDate);

}
