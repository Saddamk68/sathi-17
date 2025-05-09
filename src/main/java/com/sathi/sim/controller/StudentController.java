package com.sathi.sim.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sathi.sim.domain.Student;
import com.sathi.sim.dto.StudentDTO;
import com.sathi.sim.service.StudentService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1/student")
public class StudentController {
		
	@Autowired
	private StudentService studentService;
	
	@PostMapping()
	public ResponseEntity<Mono<StudentDTO>> insertStudentDetail(@RequestBody Student studentDetail) {
		Mono<StudentDTO> response = studentService.insertStudentDetail(studentDetail);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
	}

	@PutMapping()
	public ResponseEntity<Mono<StudentDTO>> updateStudentDetails(@RequestBody Student student) {
        return new ResponseEntity<>(studentService.updateStudentDetails(student), HttpStatus.OK);
	}

	@Transactional
	@DeleteMapping("/{studentId}")
	public void removeStudentDetails(@PathVariable(name = "studentId") Long studentId) {
		studentService.removeStudentDetails(studentId);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Mono<StudentDTO>> getStudentDetailByStudentId(@PathVariable Long id) {
		return new ResponseEntity<>(studentService.getStudentDetailByStudentId(id), HttpStatus.OK);
	}

	@GetMapping("/getStudenByName")
	public ResponseEntity<Mono<StudentDTO>> searchStudentByFirstName(@RequestParam(name = "name") String name) {
		return new ResponseEntity<>(studentService.searchStudentByFirstName(name), HttpStatus.OK);
	}

	@GetMapping()
	public ResponseEntity<Flux<StudentDTO>> getAllActiveStudent() {
		return new ResponseEntity<>(studentService.getAllActiveStudent(), HttpStatus.OK);
	}
	
}
