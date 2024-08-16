package com.sathi.sim.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sathi.sim.domain.Subject;
import com.sathi.sim.dto.SubjectDTO;
import com.sathi.sim.mapper.SubjectMapper;
import com.sathi.sim.repository.SubjectRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class SubjectServiceImpl implements SubjectService {
	
	@Autowired
	private SubjectRepository subRepo;

	@Autowired
	private SubjectMapper subMapper;
	
	@Override
	public Mono<SubjectDTO> createSubject(Subject subjectDetails) {
		Subject subject = subRepo.save(subjectDetails);
		return Mono.just(subMapper.subjectToSubjectDTO(subject));
	}
	
	@Override
	public Flux<SubjectDTO> getAllSubjects() {
		return Flux.fromIterable(subMapper.subjectToSubjectDTOList(subRepo.findAll()));
	}

	@Override
	public Mono<SubjectDTO> getSubjectByCode(String subCode) {
		return Mono.just(subMapper.subjectToSubjectDTO(subRepo.findBySubCode(subCode)));
	}

}
