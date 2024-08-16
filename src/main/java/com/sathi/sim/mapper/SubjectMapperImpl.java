package com.sathi.sim.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.sathi.sim.domain.Subject;
import com.sathi.sim.dto.SubjectDTO;

@Component
public class SubjectMapperImpl implements SubjectMapper {

	@Override
	public SubjectDTO subjectToSubjectDTO(Subject subject) {
		if (subject != null) {
			SubjectDTO subjectDto = new SubjectDTO();

			subjectDto.setId(subject.getId());
			subjectDto.setSubCode(subject.getSubCode());
			subjectDto.setSubName(subject.getSubName());

			return subjectDto;
		}
		return null;
	}

	@Override
	public List<SubjectDTO> subjectToSubjectDTOList(List<Subject> studentList) {
		if (!CollectionUtils.isEmpty(studentList)) {
			List<SubjectDTO> subjectDtoList = new ArrayList<>(studentList.size());

			for (Subject subject : studentList) {
				subjectDtoList.add(subjectToSubjectDTO(subject));
			}
			return subjectDtoList;
		}
		return null;
	}

}
