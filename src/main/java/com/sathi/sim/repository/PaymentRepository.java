package com.sathi.sim.repository;

import java.time.LocalDate;

import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;

import com.sathi.sim.domain.Payment;

import reactor.core.publisher.Flux;

@Repository
public interface PaymentRepository extends R2dbcRepository<Payment, Long> {
	
	Flux<Payment> findByStudentId(Long studentId);

//	@Query("SELECT * FROM payment_table WHERE DATE(payment_datetime) <= :remainingAmtDate")
	Flux<Payment> findByDateLessThanEqual(LocalDate remainingAmtDate);
	
}
