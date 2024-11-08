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

import com.sathi.sim.domain.Parents;
import com.sathi.sim.repository.ParentsRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1")
public class ParentsController {

	@Autowired
	private ParentsRepository parentRepo;
	
	@PostMapping("/parents")
	public ResponseEntity<Mono<Parents>> insertParentsDetail(@RequestBody Parents parentDetail) {
		return new ResponseEntity<>(parentRepo.save(parentDetail), HttpStatus.CREATED);
	}
	
	@GetMapping("/parents/{parentId}")
	public ResponseEntity<Mono<Parents>> getParentsDetailByParentId(@PathVariable(name = "parentId") Long parentId) {
		return new ResponseEntity<>(parentRepo.findByParentId(parentId), HttpStatus.OK);
	}
	
	@GetMapping("/parents")
	public ResponseEntity<Flux<Parents>> getAllParentsDetail() {
		return new ResponseEntity<>(parentRepo.findAll(), HttpStatus.OK);
	}
	
}
