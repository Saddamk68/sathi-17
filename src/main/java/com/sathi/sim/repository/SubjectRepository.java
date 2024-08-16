package com.sathi.sim.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sathi.sim.domain.Subject;

@Repository
public interface SubjectRepository extends JpaRepository<Subject, Long> {
	
	Subject findBySubCode(String subCode);
	
}
