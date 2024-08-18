package com.sathi.sim.service;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sathi.sim.domain.Payment;
import com.sathi.sim.dto.PaymentDTO;
import com.sathi.sim.exception.InvalidDateException;
import com.sathi.sim.mapper.PaymentMapper;
import com.sathi.sim.repository.PaymentRepository;
import com.sathi.sim.util.Constants;
import com.sathi.sim.validat.DateValidation;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@Service
public class PaymentServiceImpl implements PaymentService {
	
	@Autowired
	private PaymentRepository paymentRepo;

	@Autowired
	private PaymentMapper paymentMapper;
	
	@Override
	public Mono<PaymentDTO> createPayment(Payment paymentDetail) {
//		Payment payment = paymentRepo.save(paymentDetail);
		return Mono.just(paymentMapper.paymentToPaymentDTO(paymentRepo.save(paymentDetail).block()));
	}
	
	@Override
	public Flux<PaymentDTO> getPaymentByStudentId(Long studentId) {
		return Flux.fromIterable(paymentMapper.paymentToPaymentDTOList(paymentRepo.findByStudentId(studentId).collectList().block()));
	}
	
	@Override
	public Flux<PaymentDTO> getPaymentByDateLessThanEqual(String paymentDate) {
		List<PaymentDTO> response = Collections.EMPTY_LIST;
		try {
			if (DateValidation.isValidLocalDate(paymentDate)) {
				LocalDate date = DateValidation.stringToLocalDateConv(paymentDate);
				response = paymentMapper.paymentToPaymentDTOList(
						paymentRepo.findByDateLessThanEqual(date).collectList().block());
			}
		} catch (InvalidDateException e) {
			log.error(Constants.EXCPT_MSG_DATE_CONVERSION_ISSUE, paymentDate);
			return Flux.error(new InvalidDateException(String.format(
					Constants.ERR_MSG_DATE_CONVERSION_ISSUE, paymentDate)));
		}
		return Flux.fromIterable(response);
	}

}
