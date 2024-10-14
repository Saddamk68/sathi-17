package com.sathi.sim.service;

import java.time.LocalDate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sathi.sim.domain.Payment;
import com.sathi.sim.dto.PaymentDTO;
import com.sathi.sim.exception.InvalidDateException;
import com.sathi.sim.exception.PaymentDetailsNotFound;
import com.sathi.sim.mapper.PaymentMapper;
import com.sathi.sim.repository.PaymentRepository;
import com.sathi.sim.util.Constants;
import com.sathi.sim.validat.DateValidation;

import io.swagger.v3.oas.annotations.media.Schema;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class PaymentServiceImpl implements PaymentService {

	private static final Logger LOGGER = LoggerFactory.getLogger(PaymentServiceImpl.class);
	
	@Autowired
	private PaymentRepository paymentRepo;

	@Autowired
	private PaymentMapper paymentMapper;
	
	@Override
	public Mono<PaymentDTO> createPayment(Payment paymentDetail) {        
	    return paymentRepo.save(paymentDetail)
	            .map(paymentMapper::paymentToPaymentDTO)
	            .onErrorResume(e -> {
	                LOGGER.error(Constants.ERR_SAVING_PAYMENT_DET, e);
	                return Mono.error(new RuntimeException(Constants.ERR_SAVING_PAYMENT_DET, e));
	            });
	}
	
	@Override
	public Flux<PaymentDTO> getPaymentByStudentId(Long studentId) {
	    return paymentRepo.findByStudentId(studentId)
	            .switchIfEmpty(Flux.error(new PaymentDetailsNotFound(
	                    String.format(Constants.ERR_MSG_PAYMENT_DET_NOT_FOUND_FOR_GIVEN_STUDENT_ID, studentId))))
	            .map(paymentMapper::paymentToPaymentDTO);
	}
	
	@Override
	public Flux<PaymentDTO> getPaymentByDateLessThanEqual(String paymentDate) {
	    // Validate the input date format
	    if (!DateValidation.isValidLocalDate(paymentDate)) {
	    	LOGGER.error(Constants.EXCPT_MSG_DATE_CONVERSION_ISSUE, paymentDate);
	        return Flux.error(new InvalidDateException(
	                String.format(Constants.ERR_MSG_DATE_CONVERSION_ISSUE, paymentDate)));
	    }
	    
	    LocalDate date = DateValidation.stringToLocalDateConv(paymentDate);
	    
	    return paymentRepo.findByDateLessThanEqual(date)
	            .switchIfEmpty(Flux.error(new PaymentDetailsNotFound(
	                    String.format(Constants.ERR_MSG_PAYMENT_DET_NOT_FOUND_FOR_GIVEN_DATE, paymentDate))))
	            .map(paymentMapper::paymentToPaymentDTO);
	}
	
}
