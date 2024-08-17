package com.sathi.sim.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sathi.sim.domain.Payment;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {
	
	Payment findByStudentId(Long studentId);

	List<Payment> findByDateLessThanEqual(Date remainingAmtDate);
	
}
