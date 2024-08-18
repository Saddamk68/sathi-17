package com.sathi.sim.repository;

import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;

import com.sathi.sim.domain.Student;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface StudentRepository extends R2dbcRepository<Student, Long> {

	Mono<Student> findByStudentId(Long studentId);

	Mono<Student> findByFirstName(String firstName);

	void deleteByStudentId(Long StudentId);
	
	Flux<Student> findByIsActive(Boolean isActive);
		
}
