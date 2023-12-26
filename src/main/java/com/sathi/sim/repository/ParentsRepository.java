package com.sathi.sim.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sathi.sim.domain.Parents;

@Repository
public interface ParentsRepository extends JpaRepository<Parents, Long> {

	Parents findByParentId(Long parentId);
	
}
