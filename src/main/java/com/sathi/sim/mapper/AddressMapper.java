package com.sathi.sim.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.sathi.sim.domain.Address;
import com.sathi.sim.dto.AddressDTO;

@Mapper
public interface AddressMapper {

	AddressDTO addressToAddressDTO(Address address);
	
	List<AddressDTO> addressToAddressDTOList(List<Address> studentList);
	
}
