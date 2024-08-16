package com.sathi.sim.service;

import com.sathi.sim.domain.Subject;
import com.sathi.sim.dto.SubjectDTO;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface SubjectService {

	Mono<SubjectDTO> createSubject(Subject subjectDetails);
	
	Flux<SubjectDTO> getAllSubjects();

	Mono<SubjectDTO> getSubjectByCode(String subCode);

}
