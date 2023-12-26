package com.sathi.sim.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.sathi.sim.domain.Student;
import com.sathi.sim.dto.StudentDTO;

@Mapper
public interface StudentMapper {

	StudentDTO studentToStudentDTO(Student student);
	
	List<StudentDTO> studentToStudentDTOList(List<Student> student);
	
}
