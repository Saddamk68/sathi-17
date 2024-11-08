package com.sathi.sim.service;

import com.sathi.sim.domain.Payment;
import com.sathi.sim.dto.PaymentDTO;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface PaymentService {

	Mono<PaymentDTO> createPayment(Payment paymentDetails);

	Flux<PaymentDTO> getPaymentByStudentId(Long studentId);
	
	Flux<PaymentDTO> getPaymentByDateLessThanEqual(String paymentDate);

}
