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

import com.sathi.sim.domain.FeeDetail;
import com.sathi.sim.dto.FeeDetailDTO;
import com.sathi.sim.service.FeeDetailService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/fees")
public class FeeDetailController {

	@Autowired
	private FeeDetailService feeService;

	@PostMapping()
	public ResponseEntity<Mono<FeeDetailDTO>> creatSubject(@RequestBody FeeDetail paymentDetails) {
		return new ResponseEntity<>(feeService.createFeeDetail(paymentDetails), HttpStatus.CREATED);
	}

	@GetMapping("/{studentId}")
	public ResponseEntity<Mono<FeeDetailDTO>> getPaymentByStudentId(@PathVariable(value = "studentId") Long studentId) {
		return new ResponseEntity<>(feeService.getFeeDetailByStudentId(studentId), HttpStatus.OK);
	}

	@GetMapping("/remainingFeeAmount/{remainingAmt}")
	public ResponseEntity<Flux<FeeDetailDTO>> getPaymentRemainingAmtMoreThan(
			@PathVariable(value = "remainingAmt") Double remainingAmt) {
		return new ResponseEntity<>(feeService.getFeeDetailByRemainingFeeAmtMoreThan(remainingAmt), HttpStatus.OK);
	}

	@GetMapping("/remainingFeeAmtDate/{remainingAmtDate}")
	public ResponseEntity<Flux<FeeDetailDTO>> getPaymentRemainingAmtDateLessThan(
			@PathVariable(value = "remainingAmtDate") String remainingAmtDate) {
		Flux<FeeDetailDTO> response = feeService.getFeeDetailByRemainingFeeDateLessThan(remainingAmtDate);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

}
