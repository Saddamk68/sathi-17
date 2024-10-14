package com.sathi.sim.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sathi.sim.service.AdmissionService;

import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/admission-form")
public class AdmissionFormController {
	
	@Autowired
	private AdmissionService admissionService;
	
	@GetMapping("/{studentId}")
	public ResponseEntity<Mono<byte[]>> generateAdmissionFormPDF(@PathVariable(value = "studentId") Long studentId) {
	    return new ResponseEntity<>(admissionService.generateAdmissionFormPDF(studentId), HttpStatus.OK);
	}

	@GetMapping("/print/{studentId}")
	public ResponseEntity<Mono<byte[]>> printAdmissionForm(@PathVariable Long studentId) {
	    return new ResponseEntity<>(admissionService.printAdmissionForm(studentId), HttpStatus.OK);
	}

}
