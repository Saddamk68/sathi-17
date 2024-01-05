package com.sathi.sim.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sathi.sim.domain.Student;
import com.sathi.sim.dto.StudentDTO;
import com.sathi.sim.exception.InvalidRequestException;
import com.sathi.sim.mapper.StudentMapper;
import com.sathi.sim.repository.StudentRepository;
import com.sathi.sim.validat.ValidateStudentDetails;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class StudentServiceImpl implements StudentService {

	@Autowired
	private StudentRepository studentRepo;

	@Autowired
	private StudentMapper mapper;

	@Override
	public Mono<StudentDTO> insertStudentDetail(Student studentDetail) {
		if (!ValidateStudentDetails.validateStudentDetails(studentDetail)) {
			return Mono.error(new InvalidRequestException(ValidateStudentDetails.errMsg));
		}
		Student student = studentRepo.save(studentDetail);
		return Mono.just(mapper.studentToStudentDTO(student));
	}

	@Override
	public Mono<StudentDTO> updateStudentDetails(Student studentDetail) {
		if (!ValidateStudentDetails.validateStudentDetails(studentDetail)) {
			return Mono.error(new InvalidRequestException(ValidateStudentDetails.errMsg));
		}
		Student student = studentRepo.save(studentDetail);
		return Mono.just(mapper.studentToStudentDTO(student));
	}

	@Override
	public void removeStudentDetails(Long studentId) {
		studentRepo.deleteByStudentId(studentId);
	}

	@Override
	public Mono<StudentDTO> getStudentDetailByStudentId(Long studentId) {
		Student student = studentRepo.findByStudentId(studentId);
//		.switchIfEmpty(Mono.defer(() -> {
//			log.error(String.format(Constants.ERR_MSG_STUDENT_DET_NOT_FOUND_FOR_ID, studentId));
//			return Mono.error(new ResourceNotFoundException(
//					String.format(Constants.ERR_MSG_STUDENT_DET_NOT_FOUND_FOR_ID, studentId)));
//		})).block();
		return Mono.just(mapper.studentToStudentDTO(student));
	}

	@Override
	public Mono<StudentDTO> searchStudentByFirstName(String firstName) {
		Student student = studentRepo.findByFirstName(firstName);
		return Mono.just(mapper.studentToStudentDTO(student));
	}

	@Override
	public Flux<StudentDTO> getAllStudentDetail() {
		List<Student> studentList = studentRepo.findAll();
		return Flux.fromIterable(mapper.studentToStudentDTOList(studentList));
	}

}
