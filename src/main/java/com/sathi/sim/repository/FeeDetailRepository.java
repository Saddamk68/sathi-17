package com.sathi.sim.repository;

import java.time.LocalDate;

import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;

import com.sathi.sim.domain.FeeDetail;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface FeeDetailRepository extends R2dbcRepository<FeeDetail, Long> {
	
	Mono<FeeDetail> findByStudentId(Long studentId);

	Flux<FeeDetail> findByRemainingFeeAmtGreaterThanEqual(Double remainingFeeAmt);

	Flux<FeeDetail> findByRemainingFeeDateLessThanEqual(LocalDate remainingFeeDate);
	
}
