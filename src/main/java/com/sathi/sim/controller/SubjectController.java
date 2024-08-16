package com.sathi.sim.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sathi.sim.domain.Subject;
import com.sathi.sim.dto.SubjectDTO;
import com.sathi.sim.service.SubjectService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1")
public class SubjectController {
	
	@Autowired
	private SubjectService subService;
	
	@PostMapping("/subject")
	public ResponseEntity<Mono<SubjectDTO>> creatSubject(@RequestBody Subject subjectDetails) {
		return new ResponseEntity<>(subService.createSubject(subjectDetails), HttpStatus.CREATED);
	}

	@GetMapping("/subjects")
	public ResponseEntity<Flux<SubjectDTO>> getAllSubject() {
		return new ResponseEntity<>(subService.getAllSubjects(), HttpStatus.OK);
	}
	
	@GetMapping("/subject/{subCode}")
	public ResponseEntity<Mono<SubjectDTO>> getSubjectByCode(@PathVariable(value = "subCode") String subCode) {
		return new ResponseEntity<>(subService.getSubjectByCode(subCode), HttpStatus.OK);
	}
	
}
