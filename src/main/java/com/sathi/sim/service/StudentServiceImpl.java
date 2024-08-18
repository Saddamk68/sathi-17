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
	private StudentMapper studentMapper;

//	@Autowired
//	private AddressMapper addressMapper;

	@Override
	public Mono<StudentDTO> insertStudentDetail(Student studentDetail) {
		if (!ValidateStudentDetails.validateStudentDetails(studentDetail)) {
			return Mono.error(new InvalidRequestException(ValidateStudentDetails.errMsg));
		}
//		Student student = studentRepo.save(studentDetail);
		return Mono.just(studentMapper.studentToStudentDTO(studentRepo.save(studentDetail).block()));
	}

	@Override
	public Mono<StudentDTO> updateStudentDetails(Student studentDetail) {
		if (!ValidateStudentDetails.validateStudentDetails(studentDetail)) {
			return Mono.error(new InvalidRequestException(ValidateStudentDetails.errMsg));
		}
		Student student = studentRepo.save(studentDetail).block();
		return Mono.just(studentMapper.studentToStudentDTO(student));
	}

	@Override
	public void removeStudentDetails(Long studentId) {
		studentRepo.deleteByStudentId(studentId);
	}

	@Override
	public Mono<StudentDTO> getStudentDetailByStudentId(Long studentId) {
		Student student = studentRepo.findByStudentId(studentId).block();
//		.switchIfEmpty(Mono.defer(() -> {
//			log.error(String.format(Constants.ERR_MSG_STUDENT_DET_NOT_FOUND_FOR_ID, studentId));
//			return Mono.error(new ResourceNotFoundException(
//					String.format(Constants.ERR_MSG_STUDENT_DET_NOT_FOUND_FOR_ID, studentId)));
//		})).block();
		return Mono.just(studentMapper.studentToStudentDTO(student));
	}

	@Override
	public Mono<StudentDTO> searchStudentByFirstName(String firstName) {
		Student student = studentRepo.findByFirstName(firstName).block();
		return Mono.just(studentMapper.studentToStudentDTO(student));
	}

	@Override
	public Flux<StudentDTO> getAllStudent() {
		List<Student> studentList = studentRepo.findAll().collectList().block();
		return Flux.fromIterable(studentMapper.studentToStudentDTOList(studentList));
	}

	@Override
	public Flux<StudentDTO> getAllActiveStudent() {
		List<Student> studentList = studentRepo.findByIsActive(true).collectList().block();
		return Flux.fromIterable(studentMapper.studentToStudentDTOList(studentList));
	}

}
