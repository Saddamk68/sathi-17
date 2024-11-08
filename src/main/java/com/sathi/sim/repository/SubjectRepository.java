package com.sathi.sim.repository;

import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;

import com.sathi.sim.domain.Subject;

import reactor.core.publisher.Mono;

@Repository
public interface SubjectRepository extends R2dbcRepository<Subject, Long> {
	
	Mono<Subject> findBySubCode(String subCode);
	
}
