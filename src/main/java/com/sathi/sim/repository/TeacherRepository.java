package com.sathi.sim.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sathi.sim.domain.Teacher;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Long> {

	Teacher findByTeacherId(Long teacherId);

	Teacher findByTeacherName(String teacherName);

}
