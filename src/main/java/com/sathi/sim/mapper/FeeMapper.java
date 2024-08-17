package com.sathi.sim.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.sathi.sim.domain.FeeDetail;
import com.sathi.sim.dto.FeeDetailDTO;

@Mapper
public interface FeeMapper {

	FeeDetailDTO feeToFeeDTO(FeeDetail fee);
	
	List<FeeDetailDTO> feeToFeeDTOList(List<FeeDetail> fees);
	
}
