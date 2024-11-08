package com.sathi.sim.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sathi.sim.domain.Address;
import com.sathi.sim.dto.AddressDTO;
import com.sathi.sim.exception.AddressDetailsNotFound;
import com.sathi.sim.mapper.AddressMapper;
import com.sathi.sim.repository.AddressRepository;
import com.sathi.sim.util.Constants;

import reactor.core.publisher.Mono;

@Service
public class AddressServiceImpl implements AddressService {

	private static final Logger LOGGER = LoggerFactory.getLogger(AddressServiceImpl.class);
	
	@Autowired
	private AddressRepository addressRepo;

	@Autowired
	private AddressMapper addressMapper;

	@Override
	public Mono<AddressDTO> createAddress(Address addressDetails) {
		return addressRepo.save(addressDetails)
				.map(addressMapper::addressToAddressDTO)
	            .onErrorResume(e -> {
	                LOGGER.error(Constants.ERR_SAVING_ADDRESS_DET, e.getMessage());
	                return Mono.error(new RuntimeException(Constants.ERR_SAVING_ADDRESS_DET, e));
	            });
	}

	@Override
	public void removeAddressDetails(Long addressId) {
		addressRepo.deleteById(addressId)
		.onErrorResume(e -> {
            LOGGER.error(Constants.ERR_DELETE_ADDRESS_DET, e.getMessage());
            return Mono.error(new RuntimeException(Constants.ERR_DELETE_ADDRESS_DET, e));
        });
	}

	@Override
	public Mono<AddressDTO> getAddressById(Long addressId) {
		return addressRepo.findById(addressId)
	            .switchIfEmpty(Mono.error(new AddressDetailsNotFound(
	                    String.format(Constants.ERR_MSG_ADDR_DET_NOT_FOUND_FOR_GIVEN_ADDR_ID, addressId))))
	            .map(addressMapper::addressToAddressDTO);
	}
	
}
