package com.sathi.sim.service;

import com.sathi.sim.domain.Student;
import com.sathi.sim.dto.StudentDTO;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface StudentService {

	public Mono<StudentDTO> insertStudentDetail(Student studentDetail);
	
	public Mono<StudentDTO> updateStudentDetails(Student studentDetail);
	
	public void removeStudentDetails(Long studentId);
	
	public Mono<StudentDTO> getStudentDetailByStudentId(Long studentId);
	
	public Mono<StudentDTO> searchStudentByFirstName(String firstName);
	
	public Flux<StudentDTO> getAllStudentDetail();
	
}
