package com.sathi.sim.service;

import com.sathi.sim.domain.Address;
import com.sathi.sim.dto.AddressDTO;

import reactor.core.publisher.Mono;

public interface AddressService {
	
	public Mono<AddressDTO> createAddress(Address addressDetails);

	public void removeAddressDetails(Long addressId);
	
	public Mono<AddressDTO> getAddressById(Long addressId);

}
