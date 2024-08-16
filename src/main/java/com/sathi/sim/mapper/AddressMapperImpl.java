package com.sathi.sim.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.sathi.sim.domain.Address;
import com.sathi.sim.domain.Student;
import com.sathi.sim.dto.AddressDTO;
import com.sathi.sim.dto.StudentDTO;

@Component
public class AddressMapperImpl implements AddressMapper {

	@Override
	public AddressDTO addressToAddressDTO(Address address) {
		if (address != null) {
			AddressDTO addressDto = new AddressDTO();

			addressDto.setId(address.getId());
			addressDto.setFirstLine(address.getFirstLine());
			addressDto.setSecondLine(address.getSecondLine());
			addressDto.setState(address.getState());
			addressDto.setCity(address.getCity());
			addressDto.setPinCode(address.getPinCode());

			return addressDto;
		}
		return null;
	}

	@Override
	public List<AddressDTO> addressToAddressDTOList(List<Address> addressList) {
		if (!CollectionUtils.isEmpty(addressList)) {
			List<AddressDTO> addressDtoList = new ArrayList<>(addressList.size());

			for (Address address : addressList) {
				addressDtoList.add(addressToAddressDTO(address));
			}
			return addressDtoList;
		}
		return null;
	}

}
