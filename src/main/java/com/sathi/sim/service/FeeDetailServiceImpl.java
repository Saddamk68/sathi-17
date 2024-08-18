package com.sathi.sim.service;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sathi.sim.domain.FeeDetail;
import com.sathi.sim.domain.Payment;
import com.sathi.sim.dto.FeeDetailDTO;
import com.sathi.sim.dto.PaymentDTO;
import com.sathi.sim.exception.InvalidDateException;
import com.sathi.sim.mapper.FeeMapper;
import com.sathi.sim.repository.FeeDetailRepository;
import com.sathi.sim.util.Constants;
import com.sathi.sim.validat.DateValidation;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@Service
public class FeeDetailServiceImpl implements FeeDetailService {
	
	@Autowired
	private FeeDetailRepository feeRepo;
	
	@Autowired
	private FeeMapper feeMapper;

	@Override
	public Mono<FeeDetailDTO> createFeeDetail(FeeDetail feeDetail) {
		FeeDetail fee = feeRepo.save(feeDetail).block();
		return Mono.just(feeMapper.feeToFeeDTO(fee));
	}

	@Override
	public Mono<FeeDetailDTO> getFeeDetailByStudentId(Long studentId) {
		return Mono.just(feeMapper.feeToFeeDTO(feeRepo.findByStudentId(studentId).block()));
	}

	@Override
	public Flux<FeeDetailDTO> getFeeDetailByRemainingFeeAmtMoreThan(Double remainingFeeAmt) {
		return Flux.fromIterable(feeMapper.feeToFeeDTOList(
				feeRepo.findByRemainingFeeAmtGreaterThanEqual(remainingFeeAmt).collectList().block()));
	}

	@Override
	public Flux<FeeDetailDTO> getFeeDetailByRemainingFeeDateLessThan(String remainingFeeAmtDate) {
		List<FeeDetailDTO> response = Collections.EMPTY_LIST;
		try {
			if (DateValidation.isValidDate(remainingFeeAmtDate)) {
				Date date = DateValidation.stringToDateConv(remainingFeeAmtDate);
				response = feeMapper.feeToFeeDTOList(
						feeRepo.findByRemainingFeeDateLessThanEqual(date).collectList().block());
			}
		} catch (InvalidDateException e) {
			log.error(Constants.EXCPT_MSG_DATE_CONVERSION_ISSUE, remainingFeeAmtDate);
			return Flux.error(new InvalidDateException(String.format(
					Constants.ERR_MSG_DATE_CONVERSION_ISSUE, remainingFeeAmtDate)));
		}
		return Flux.fromIterable(response);
	}

}
