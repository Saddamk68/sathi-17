package com.sathi.sim.mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.sathi.sim.domain.Student;
import com.sathi.sim.domain.Subject;
import com.sathi.sim.dto.StudentDTO;

@Component
public class StudentMapperImpl implements StudentMapper {

	@Autowired()
	private AddressMapper addressMapper;

	@Override
	public StudentDTO studentToStudentDTO(Student student) {
		if (student != null) {
			StudentDTO studentDto = new StudentDTO();

			studentDto.setContactNum(student.getContactNum());
			studentDto.setEmail(student.getEmail());
			studentDto.setFirstName(student.getFirstName());
			studentDto.setLastName(student.getLastName());
			if (!StringUtils.isBlank(student.getMiddleName())) {
				studentDto.setMiddleName(student.getMiddleName());
			}
			studentDto.setFatherName(student.getFatherName());
			studentDto.setMotherName(student.getMotherName());
			studentDto.setClassName(student.getClassName());
			studentDto.setDob(student.getDob());
			studentDto.setGender(student.getGender());
			studentDto.setStudentId(student.getStudentId());
			studentDto.setSchoolName(student.getSchoolName());
			studentDto.setAddress(addressMapper.addressToAddressDTO(student.getAddress()));
			Set<String> subjects = student.getSubjects().stream()
					.map(Subject::getSubName)
					.collect(Collectors.toSet());
			studentDto.setSubjects(subjects);
			studentDto.setIsActive(student.getIsActive());
			studentDto.setImageUrl(student.getImageUrl());

			return studentDto;
		}
		return null;
	}

	@Override
	public List<StudentDTO> studentToStudentDTOList(List<Student> studentList) {
		if (!CollectionUtils.isEmpty(studentList)) {
			List<StudentDTO> studentDtoList = new ArrayList<>(studentList.size());

			for (Student student : studentList) {
				studentDtoList.add(studentToStudentDTO(student));
			}
			return studentDtoList;
		}
		return null;
	}

}
