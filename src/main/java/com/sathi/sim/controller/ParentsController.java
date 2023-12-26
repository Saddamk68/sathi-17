package com.sathi.sim.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sathi.sim.domain.Parents;
import com.sathi.sim.repository.ParentsRepository;

@RestController
@RequestMapping("/api/v1")
public class ParentsController {

	@Autowired
	private ParentsRepository parentRepo;
	
	@PostMapping("/parents")
	public Parents insertParentsDetail(@RequestBody Parents parentDetail) {
		return parentRepo.save(parentDetail);
	}
	
	@GetMapping("/parents/{parentId}")
	public Parents getParentsDetailByParentId(@PathVariable(name = "parentId") Long parentId) {
		return parentRepo.findByParentId(parentId);
	}
	
	@GetMapping("/parents")
	public List<Parents> getAllParentsDetail() {
		return parentRepo.findAll();
	}
	
}
