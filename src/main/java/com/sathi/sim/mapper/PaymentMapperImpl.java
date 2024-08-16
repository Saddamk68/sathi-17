package com.sathi.sim.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.sathi.sim.domain.Payment;
import com.sathi.sim.dto.PaymentDTO;

@Component
public class PaymentMapperImpl implements PaymentMapper {

	@Override
	public PaymentDTO paymentToPaymentDTO(Payment payment) {
		if (payment != null) {
			PaymentDTO paymentDto = new PaymentDTO();

			paymentDto.setId(payment.getId());
			paymentDto.setStudentId(payment.getStudentId());
			paymentDto.setAmount(payment.getAmount());
			paymentDto.setDate(payment.getDate());
			paymentDto.setRemainingAmt(payment.getRemainingAmt());
			paymentDto.setRemainingAmtDate(payment.getRemainingAmtDate());
			paymentDto.setPaymentMethod(payment.getPaymentMethod());

			return paymentDto;
		}
		return null;
	}

	@Override
	public List<PaymentDTO> paymentToPaymentDTOList(List<Payment> paymentList) {
		if (!CollectionUtils.isEmpty(paymentList)) {
			List<PaymentDTO> paymentDtoList = new ArrayList<>(paymentList.size());

			for (Payment payment : paymentList) {
				paymentDtoList.add(paymentToPaymentDTO(payment));
			}
			return paymentDtoList;
		}
		return null;
	}

}
