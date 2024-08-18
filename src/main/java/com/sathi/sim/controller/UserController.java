package com.sathi.sim.controller;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sathi.sim.domain.User;
import com.sathi.sim.repository.UserRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1")
public class UserController {

	@Autowired
	private UserRepository userRepository;

	@PostMapping("/user")
	public ResponseEntity<Mono<User>> createUser(@RequestBody User user) {
		return new ResponseEntity<>(userRepository.save(user), HttpStatus.CREATED);
	}

	@PutMapping("/user/update/{id}")
	public ResponseEntity<Mono<User>> updateUser(@PathVariable(value = "id") Long userId, @RequestBody User userDetails) {
		User user = userRepository.findById(userId).block();

		user.setEmail(userDetails.getEmail());
		user.setLastName(userDetails.getLastName());
		user.setFirstName(userDetails.getFirstName());
		user.setUpdatedAt(LocalDateTime.now());
		
		return new ResponseEntity<>(userRepository.save(user), HttpStatus.OK);
	}

	@DeleteMapping("/user/delete/{id}")
	public ResponseEntity<Mono<User>> deleteUser(@PathVariable(value = "id") Long userId) {
		User user = userRepository.findById(userId).block();
		userRepository.delete(user);
		return new ResponseEntity<>(Mono.just(user), HttpStatus.OK);
	}

	@GetMapping("/users")
	public ResponseEntity<Flux<User>> getAllUsers() {
		return new ResponseEntity<>(userRepository.findAll(), HttpStatus.OK);
	}

	@GetMapping("/user/{id}")
	public ResponseEntity<Mono<User>> getUsersById(@PathVariable(value = "id") Long userId) {
		return new ResponseEntity<>(userRepository.findById(userId), HttpStatus.OK);
	}

}
