package com.sathi.sim.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sathi.sim.domain.Address;
import com.sathi.sim.dto.AddressDTO;
import com.sathi.sim.service.AddressService;

import reactor.core.publisher.Mono;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1/address")
public class AddressController {
	
	@Autowired
	private AddressService addrService;

	@PostMapping()
	public ResponseEntity<Mono<AddressDTO>> insertStudentDetail(@RequestBody Address addressDetail) {
		Mono<AddressDTO> response = addrService.createAddress(addressDetail);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
	}

	@Transactional
	@DeleteMapping("/{addressId}")
	public void removeStudentDetails(@PathVariable(name = "addressId") Long addressId) {
		addrService.removeAddressDetails(addressId);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Mono<AddressDTO>> getStudentDetailByStudentId(@PathVariable(name = "id") Long addressId) {
		return new ResponseEntity<>(addrService.getAddressById(addressId), HttpStatus.OK);
	}	

}
