package com.sathi.sim.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.sathi.sim.domain.Payment;
import com.sathi.sim.dto.PaymentDTO;

@Mapper
public interface PaymentMapper {

	PaymentDTO paymentToPaymentDTO(Payment payment);
	
	List<PaymentDTO> paymentToPaymentDTOList(List<Payment> paymentList);
	
}
