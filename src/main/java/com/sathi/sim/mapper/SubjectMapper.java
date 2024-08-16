package com.sathi.sim.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.sathi.sim.domain.Subject;
import com.sathi.sim.dto.SubjectDTO;

@Mapper
public interface SubjectMapper {

	SubjectDTO subjectToSubjectDTO(Subject subject);
	
	List<SubjectDTO> subjectToSubjectDTOList(List<Subject> subjectList);
	
}
