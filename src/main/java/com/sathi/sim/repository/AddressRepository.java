package com.sathi.sim.repository;

import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;

import com.sathi.sim.domain.Address;
import com.sathi.sim.domain.Subject;

import reactor.core.publisher.Flux;

@Repository
public interface AddressRepository extends R2dbcRepository<Address, Long> {
	
	Flux<Subject> findByCity(String city);
	
}
