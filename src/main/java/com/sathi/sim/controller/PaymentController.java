package com.sathi.sim.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sathi.sim.domain.Payment;
import com.sathi.sim.dto.PaymentDTO;
import com.sathi.sim.service.PaymentService;

import io.swagger.v3.oas.annotations.media.Schema;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/payment")
@Schema(description = "Details about the Payments")
public class PaymentController {

	@Autowired
	private PaymentService paymentService;

	@PostMapping()
	@Schema(description = "Creates new payment details")
	public ResponseEntity<Mono<PaymentDTO>> creatSubject(@RequestBody Payment paymentDetails) {
		return new ResponseEntity<>(paymentService.createPayment(paymentDetails), HttpStatus.CREATED);
	}

	@GetMapping("/{studentId}")
	@Schema(description = "Get the payment details for given studentId")
	public ResponseEntity<Flux<PaymentDTO>> getPaymentByStudentId(@PathVariable(value = "studentId") Long studentId) {
		return new ResponseEntity<>(paymentService.getPaymentByStudentId(studentId), HttpStatus.OK);
	}
	
	@GetMapping("/paymentDate/{paymentDate}")
	@Schema(description = "Get the payment details for the given date")
	public ResponseEntity<Flux<PaymentDTO>> getPaymentRemainingAmtDateLessThan(
			@PathVariable(value = "paymentDate") String paymentDate) {
		Flux<PaymentDTO> response = paymentService.getPaymentByDateLessThanEqual(paymentDate);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

}
