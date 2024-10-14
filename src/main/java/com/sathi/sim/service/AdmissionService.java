package com.sathi.sim.service;

import reactor.core.publisher.Mono;

public interface AdmissionService {

	public Mono<byte[]> generateAdmissionFormPDF(Long studentId);

	public Mono<byte[]> printAdmissionForm(Long studentId);
	
}
