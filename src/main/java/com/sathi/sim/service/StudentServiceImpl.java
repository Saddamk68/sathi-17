package com.sathi.sim.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sathi.sim.domain.Student;
import com.sathi.sim.dto.StudentDTO;
import com.sathi.sim.exception.AddressDetailsNotFound;
import com.sathi.sim.exception.FeeDetailsNotFound;
import com.sathi.sim.exception.InvalidRequestException;
import com.sathi.sim.exception.StudentDetailsNotFound;
import com.sathi.sim.mapper.StudentMapper;
import com.sathi.sim.repository.AddressRepository;
import com.sathi.sim.repository.CustomStudentRepository;
import com.sathi.sim.repository.StudentRepository;
import com.sathi.sim.repository.StudentSubjectRepository;
import com.sathi.sim.repository.SubjectRepository;
import com.sathi.sim.util.Constants;
import com.sathi.sim.validat.ValidateStudentDetails;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class StudentServiceImpl implements StudentService {

	private static final Logger LOGGER = LoggerFactory.getLogger(StudentServiceImpl.class);
	
	@Autowired
	private StudentRepository studentRepo;

	@Autowired
	private StudentSubjectRepository studentSubRepo;

	@Autowired
	private SubjectRepository subRepo;

	@Autowired
	private AddressRepository addressRepo;

	@Autowired
	private CustomStudentRepository customStudentRepository;

	@Autowired
	private StudentMapper studentMapper;

//	@Autowired
//	private AddressMapper addressMapper;

	@Override
	public Mono<StudentDTO> insertStudentDetail(Student studentDetail) {
		if (!ValidateStudentDetails.validateStudentDetails(studentDetail)) {
			return Mono.error(new InvalidRequestException(ValidateStudentDetails.errMsg));
		}
		
		return studentRepo.save(studentDetail)
				.map(studentMapper::studentToStudentDTO)
	            .onErrorResume(e -> {
	                LOGGER.error(Constants.ERR_SAVING_FEE_DET, e.getMessage());
	                return Mono.error(new RuntimeException(Constants.ERR_SAVING_FEE_DET, e));
	            });
	}

	@Override
	public Mono<StudentDTO> updateStudentDetails(Student studentDetail) {
		if (!ValidateStudentDetails.validateStudentDetails(studentDetail)) {
			return Mono.error(new InvalidRequestException(ValidateStudentDetails.errMsg));
		}
		
		return studentRepo.save(studentDetail)
				.map(studentMapper::studentToStudentDTO)
	            .onErrorResume(e -> {
	                LOGGER.error(Constants.ERR_UPDATE_STUDENT_DET, e.getMessage());
	                return Mono.error(new RuntimeException(Constants.ERR_UPDATE_STUDENT_DET, e));
	            });
	}

	@Override
	public void removeStudentDetails(Long studentId) {
		studentRepo.deleteByStudentId(studentId)
		.onErrorResume(e -> {
            LOGGER.error(Constants.ERR_DELETE_STUDENT_DET, e.getMessage());
            return Mono.error(new RuntimeException(Constants.ERR_DELETE_STUDENT_DET, e));
        });
	}

	@Override
	public Mono<StudentDTO> getStudentDetailByStudentId(Long studentId) {
		return studentRepo.findByStudentId(studentId)
	            .switchIfEmpty(Mono.error(new FeeDetailsNotFound(
	                    String.format(Constants.ERR_MSG_STUDENT_DET_NOT_FOUND_FOR_GIVEN_STUDENT_ID, studentId))))
	            .map(studentMapper::studentToStudentDTO);
		
//	    return studentRepo.findByStudentId(studentId)
//	            .switchIfEmpty(Mono.error(new StudentDetailsNotFound(String.format(Constants.ERR_MSG_STUDENT_DET_NOT_FOUND_FOR_GIVEN_STUDENT_ID, studentId))))
//	            .flatMap(student -> 
//	                addressRepo.findById(student.getAddressId())
//	                    .switchIfEmpty(Mono.error(new AddressDetailsNotFound("Address not found for student ID: " + studentId))) // Handle empty address
//	                    .flatMap(address -> 
//	                    	studentSubRepo.findByStudentId(student.getStudentId())
//	                    		.flatMap(studentSub -> 
//	                    			subRepo.findbyId(studentSub.getSubjectid())
//	                    				.collectList()
//	    	                            .defaultIfEmpty(Collections.emptyList()) 
//	    	                            .map(subjects -> new StudentDetailsDTO(student, address, subjects))
//	                    		)
//	                    )
//	            );
	}

	@Override
	public Flux<StudentDTO> searchStudentByFirstName(String firstName) {
		return studentRepo.findByFirstName(firstName)
	            .switchIfEmpty(Flux.error(new StudentDetailsNotFound(
	                    String.format(Constants.ERR_MSG_STUDENT_DET_NOT_FOUND_FOR_GIVEN_STUDENT_NAME, firstName))))
	            .map(studentMapper::studentToStudentDTO);
	}

	@Override
	public Flux<StudentDTO> getAllStudent() {
		return studentRepo.findAll()
				.switchIfEmpty(Flux.error(new StudentDetailsNotFound(Constants.ERR_MSG_STUDENT_RECORD_NOT_FOUND)))
	            .map(studentMapper::studentToStudentDTO);
	}

	@Override
	public Flux<StudentDTO> getAllActiveStudent() {
		return customStudentRepository.fetchStudentsWithAddressAndSubjects()
				.switchIfEmpty(Flux.error(new StudentDetailsNotFound(Constants.ERR_MSG_NO_ACTIVE_STUDENT_RECORD_FOUND)));
	}

}
