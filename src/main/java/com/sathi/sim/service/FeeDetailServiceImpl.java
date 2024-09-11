package com.sathi.sim.service;

import java.time.LocalDate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sathi.sim.domain.FeeDetail;
import com.sathi.sim.dto.FeeDetailDTO;
import com.sathi.sim.exception.FeeDetailsNotFound;
import com.sathi.sim.exception.InvalidDateException;
import com.sathi.sim.exception.PaymentDetailsNotFound;
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

	private static final Logger LOGGER = LoggerFactory.getLogger(FeeDetailServiceImpl.class);
	
	@Autowired
	private FeeDetailRepository feeRepo;
	
	@Autowired
	private FeeMapper feeMapper;

	@Override
	public Mono<FeeDetailDTO> createFeeDetail(FeeDetail feeDetail) {
		return feeRepo.save(feeDetail)
	            .map(feeMapper::feeToFeeDTO)
	            .onErrorResume(e -> {
	                LOGGER.error(Constants.ERR_SAVING_FEE_DET, e);
	                return Mono.error(new RuntimeException(Constants.ERR_SAVING_FEE_DET, e));
	            });
	}

	@Override
	public Mono<FeeDetailDTO> getFeeDetailByStudentId(Long studentId) {
		return feeRepo.findByStudentId(studentId)
	            .switchIfEmpty(Mono.error(new FeeDetailsNotFound(
	                    String.format(Constants.ERR_MSG_FEE_DET_NOT_FOUND_FOR_GIVEN_STUDENT_ID, studentId))))
	            .map(feeMapper::feeToFeeDTO);
	}

	@Override
	public Flux<FeeDetailDTO> getFeeDetailByRemainingFeeAmtMoreThan(Double remainingFeeAmt) {
		return feeRepo.findByRemainingFeeAmtGreaterThanEqual(remainingFeeAmt)
	            .switchIfEmpty(Flux.error(new FeeDetailsNotFound(
	                    String.format(Constants.ERR_MSG_FEE_DET_NOT_FOUND_FOR_GIVEN_REMAINING_AMT, remainingFeeAmt))))
	            .map(feeMapper::feeToFeeDTO);
	}

	@Override
	public Flux<FeeDetailDTO> getFeeDetailByRemainingFeeDateLessThan(String remainingFeeAmtDate) {     
	    // Validate the input date format
	    if (!DateValidation.isValidLocalDate(remainingFeeAmtDate)) {
	    	LOGGER.error(Constants.EXCPT_MSG_DATE_CONVERSION_ISSUE, remainingFeeAmtDate);
	        return Flux.error(new InvalidDateException(
	                String.format(Constants.ERR_MSG_DATE_CONVERSION_ISSUE, remainingFeeAmtDate)));
	    }

	    LocalDate date = DateValidation.stringToLocalDateConv(remainingFeeAmtDate);
	    
	    return feeRepo.findByRemainingFeeDateLessThanEqual(date)
	            .switchIfEmpty(Flux.error(new PaymentDetailsNotFound(
	                    String.format(Constants.ERR_MSG_FEE_DET_NOT_FOUND_FOR_GIVEN_DATE, remainingFeeAmtDate))))
	            .map(feeMapper::feeToFeeDTO);
	}

}
