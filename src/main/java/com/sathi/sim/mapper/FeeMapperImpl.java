package com.sathi.sim.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.sathi.sim.domain.FeeDetail;
import com.sathi.sim.dto.FeeDetailDTO;

@Component
public class FeeMapperImpl implements FeeMapper {

	@Override
	public FeeDetailDTO feeToFeeDTO(FeeDetail fee) {
		if (fee != null) {
			FeeDetailDTO feeDto = new FeeDetailDTO();

			feeDto.setId(fee.getId());
			feeDto.setStudentId(fee.getStudentId());
			feeDto.setTotalFee(fee.getTotalFee());
			feeDto.setDepositFeeAmt(fee.getDepositFeeAmt());
			feeDto.setDepositFeeDate(fee.getDepositFeeDate());
			feeDto.setRemainingFeeAmt(fee.getRemainingFeeAmt());
			feeDto.setRemainingFeeDate(fee.getRemainingFeeDate());

			return feeDto;
		}
		return null;
	}

	@Override
	public List<FeeDetailDTO> feeToFeeDTOList(List<FeeDetail> fees) {
		if (!CollectionUtils.isEmpty(fees)) {
			List<FeeDetailDTO> feeDtoList = new ArrayList<>(fees.size());

			for (FeeDetail fee : fees) {
				feeDtoList.add(feeToFeeDTO(fee));
			}
			return feeDtoList;
		}
		return null;
	}

}
