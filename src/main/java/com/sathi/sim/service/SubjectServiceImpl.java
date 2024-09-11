package com.sathi.sim.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sathi.sim.domain.Subject;
import com.sathi.sim.dto.SubjectDTO;
import com.sathi.sim.exception.FeeDetailsNotFound;
import com.sathi.sim.mapper.SubjectMapper;
import com.sathi.sim.repository.SubjectRepository;
import com.sathi.sim.util.Constants;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class SubjectServiceImpl implements SubjectService {

	private static final Logger LOGGER = LoggerFactory.getLogger(SubjectServiceImpl.class);
	
	@Autowired
	private SubjectRepository subRepo;

	@Autowired
	private SubjectMapper subMapper;
	
	@Override
	public Mono<SubjectDTO> createSubject(Subject subjectDetails) {
		return subRepo.save(subjectDetails)
	            .map(subMapper::subjectToSubjectDTO)
	            .onErrorResume(e -> {
	                LOGGER.error(Constants.ERR_SAVING_SUBJECT_DET, e);
	                return Mono.error(new RuntimeException(Constants.ERR_SAVING_SUBJECT_DET, e));
	            });
	}
	
	@Override
	public Flux<SubjectDTO> getAllSubjects() {
		return subRepo.findAll()
	            .switchIfEmpty(Flux.error(new FeeDetailsNotFound(Constants.ERR_MSG_SUBJECT_DET_NOT_FOUND)))
	            .map(subMapper::subjectToSubjectDTO);
	}

	@Override
	public Mono<SubjectDTO> getSubjectByCode(String subCode) {
		return subRepo.findBySubCode(subCode)
	            .switchIfEmpty(Mono.error(new FeeDetailsNotFound(
	            		String.format(Constants.ERR_MSG_SUBJECT_DET_NOT_FOUND_FOR_GIVEN_SUB_CODE, subCode))))
	            .map(subMapper::subjectToSubjectDTO);
	}

}
