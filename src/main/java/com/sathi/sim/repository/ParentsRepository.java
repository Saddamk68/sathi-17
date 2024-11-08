package com.sathi.sim.repository;

import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;

import com.sathi.sim.domain.Parents;

import reactor.core.publisher.Mono;

@Repository
public interface ParentsRepository extends R2dbcRepository<Parents, Long> {

	Mono<Parents> findByParentId(Long parentId);
	
}
