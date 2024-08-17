package com.sathi.sim.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sathi.sim.domain.FeeDetail;

@Repository
public interface FeeDetailRepository extends JpaRepository<FeeDetail, Long> {
	
	FeeDetail findByStudentId(Long studentId);

	List<FeeDetail> findByRemainingFeeAmtGreaterThanEqual(Double remainingFeeAmt);

	List<FeeDetail> findByRemainingFeeDateLessThanEqual(Date remainingFeeDate);
	
}
