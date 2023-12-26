package com.sathi.sim.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sathi.sim.domain.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

	Student findByStudentId(Long studentId);

	Student findByFirstName(String firstName);

	void deleteByStudentId(Long StudentId);
		
}
