package com.sathi.sim.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sathi.sim.domain.Payment;
import com.sathi.sim.dto.PaymentDTO;
import com.sathi.sim.exception.InvalidRequestException;
import com.sathi.sim.mapper.PaymentMapper;
import com.sathi.sim.repository.PaymentRepository;
import com.sathi.sim.util.Constants;

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
		Payment payment = paymentRepo.save(paymentDetail);
		return Mono.just(paymentMapper.paymentToPaymentDTO(payment));
	}
	
	@Override
	public Mono<PaymentDTO> getPaymentByStudentId(Long studentId) {
		return Mono.just(paymentMapper.paymentToPaymentDTO(paymentRepo.findByStudentId(studentId)));
	}

	@Override
	public Flux<PaymentDTO> getPaymentRemainingAmtMoreThan(Double remainingAmt) {
		return Flux.fromIterable(paymentMapper.paymentToPaymentDTOList(
				paymentRepo.findByRemainingAmtGreaterThanEqual(remainingAmt)));
	}

	@Override
	public Flux<PaymentDTO> getPaymentRemainingAmtDateLessThan(String remainingAmtDate) throws InvalidRequestException {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date date = simpleDateFormat.parse(remainingAmtDate);
			return Flux.fromIterable(paymentMapper.paymentToPaymentDTOList(
					paymentRepo.findByRemainingAmtDateLessThanEqual(date)));
		} catch (ParseException e) {
			log.error(String.format(Constants.ERR_MSG_DATE_CONVERSION_ISSUE, remainingAmtDate));
			throw new InvalidRequestException(Constants.EXCPT_MSG_DATE_CONVERSION_ISSUE + remainingAmtDate);
		}
	}

}
