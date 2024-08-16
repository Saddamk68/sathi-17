package com.sathi.sim.service;

import com.sathi.sim.domain.Payment;
import com.sathi.sim.dto.PaymentDTO;
import com.sathi.sim.exception.InvalidRequestException;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface PaymentService {

	Mono<PaymentDTO> createPayment(Payment paymentDetails);

	Mono<PaymentDTO> getPaymentByStudentId(Long studentId);
	
	Flux<PaymentDTO> getPaymentRemainingAmtMoreThan(Double remainingAmt);

	Flux<PaymentDTO> getPaymentRemainingAmtDateLessThan(String remainingAmtDate) throws InvalidRequestException;

}
