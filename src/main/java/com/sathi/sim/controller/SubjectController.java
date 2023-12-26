package com.sathi.sim.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sathi.sim.domain.Subject;
import com.sathi.sim.repository.SubjectRepository;

@RestController
@RequestMapping("/api/v1")
public class SubjectController {
	
	@Autowired
	private SubjectRepository subRepo;
	
	@PostMapping("/subject")
	public Subject creatSubject(@RequestBody Subject subject) {
		return subRepo.save(subject);
	}

	@GetMapping("/subjects")
	public List<Subject> getAllSubject() {
		return subRepo.findAll();
	}
	
	@GetMapping("/subject/{subName}")
	public Subject getSubjectByName(@PathVariable(value = "subName") String subName) {
		return subRepo.findBySubName(subName);
	}
	
}
