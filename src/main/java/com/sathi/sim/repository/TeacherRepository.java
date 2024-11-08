package com.sathi.sim.repository;

import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;

import com.sathi.sim.domain.Teacher;

import reactor.core.publisher.Mono;

@Repository
public interface TeacherRepository extends R2dbcRepository<Teacher, Long> {

	Mono<Teacher> findByTeacherId(Long teacherId);

	Mono<Teacher> findByTeacherName(String teacherName);

}
