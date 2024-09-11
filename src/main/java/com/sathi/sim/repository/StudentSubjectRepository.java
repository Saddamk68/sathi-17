package com.sathi.sim.repository;

import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;

import com.sathi.sim.domain.Address;
import com.sathi.sim.domain.StudentSubject;
import com.sathi.sim.domain.Subject;

import reactor.core.publisher.Flux;

@Repository
public interface StudentSubjectRepository extends R2dbcRepository<StudentSubject, Long> {
	
	Flux<StudentSubject> findByStudentId(Long studentId);
	
}
