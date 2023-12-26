package com.sathi.sim.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sathi.sim.domain.Teacher;
import com.sathi.sim.repository.TeacherRepository;

@RestController
@RequestMapping("/api/v1")
public class TeacherController {

	@Autowired
	private TeacherRepository teacherRepo;
	

	@PostMapping("/teacher")
	public Teacher insertTeacherDetail(@RequestBody Teacher teacherDetail) {
		return teacherRepo.save(teacherDetail);
	}
	
	@GetMapping("/teacher/{teacherId}")
	public Teacher getTeacherDetailByTeacherId(@PathVariable(name = "teacherId") Long teacherId) {
		return teacherRepo.findByTeacherId(teacherId);
	}

	@GetMapping("/teacher/{teacherName}")
	public Teacher getTeacherDetailByTeacherName(@PathVariable(name = "teacherName") String teacherName) {
		return teacherRepo.findByTeacherName(teacherName);
	}

	@GetMapping("/teacher")
	public List<Teacher> getAllTeachersDetail() {
		return teacherRepo.findAll();
	}

	
}
