package com.sathi.sim.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sathi.sim.domain.Teacher;
import com.sathi.sim.repository.TeacherRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1")
public class TeacherController {

	@Autowired
	private TeacherRepository teacherRepo;
	

	@PostMapping("/teacher")
	public ResponseEntity<Mono<Teacher>> insertTeacherDetail(@RequestBody Teacher teacherDetail) {
		return new ResponseEntity<>(teacherRepo.save(teacherDetail), HttpStatus.CREATED);
	}
	
	@GetMapping("/teacher/{teacherId}")
	public ResponseEntity<Mono<Teacher>> getTeacherDetailByTeacherId(@PathVariable(name = "teacherId") Long teacherId) {
		return new ResponseEntity<>(teacherRepo.findByTeacherId(teacherId), HttpStatus.OK);
	}

	@GetMapping("/teacher/{teacherName}")
	public ResponseEntity<Mono<Teacher>> getTeacherDetailByTeacherName(@PathVariable(name = "teacherName") String teacherName) {
		return new ResponseEntity<>(teacherRepo.findByTeacherName(teacherName), HttpStatus.OK);
	}

	@GetMapping("/teacher")
	public ResponseEntity<Flux<Teacher>> getAllTeachersDetail() {
		return new ResponseEntity<>(teacherRepo.findAll(), HttpStatus.OK);
	}

	
}
