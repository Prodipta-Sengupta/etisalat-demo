package com.example.etisalat.controller;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.etisalat.dao.User;
import com.example.etisalat.model.request.UserRequest;
import com.example.etisalat.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/User")
public class UserController {

	@Autowired
	UserService service;
	@Autowired
	ModelMapper modelMapper;

	@GetMapping("/users")
	public ResponseEntity<List<User>> getAllUsers() {
		List<User> allUsers = service.getAllUsers();
		return new ResponseEntity<List<User>>(allUsers, HttpStatus.OK);
	}

	@GetMapping("/user")
	public ResponseEntity<User> getUserByNameOrEmail(@RequestParam Optional<String> name,
			@RequestParam Optional<String> email) {
		User user = null;
		if (name.isPresent()) {
			user = service.getUserByName(name.get());

		} else if (email.isPresent()) {
			user = service.getUserByEmail(email.get());
		}
		if (user != null) {
			return new ResponseEntity<User>(user, HttpStatus.OK);
		}
		return new ResponseEntity<User>(HttpStatus.NOT_FOUND);

	}

	@PostMapping("/user")
	public ResponseEntity<User> createUser(@Valid @RequestBody UserRequest user) {
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
		User mappedUser = modelMapper.map(user, User.class);
		User createdUser = service.createOrUpdateUser(mappedUser);
		if (createdUser != null) {
			return new ResponseEntity<User>(createdUser, HttpStatus.CREATED);
		}
		return null;

	}

	@PutMapping("/user")
	public ResponseEntity<User> updateUser(@Valid @RequestBody UserRequest userRequest) {
		User user = service.getUserByName(userRequest.getName());
		if (user == null) {
			user = service.getUserByEmail(userRequest.getEmail());
		}
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
		if (user != null) {
			Integer id = user.getId();
			user = modelMapper.map(userRequest, User.class);
			user.setId(id);
			User updatedUser = service.createOrUpdateUser(user);
			return new ResponseEntity<User>(updatedUser, HttpStatus.OK);
		}
		return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
	}

	@DeleteMapping("/user")
	public ResponseEntity deleteUserByNameorEmail(@RequestParam Optional<String> name,
			@RequestParam Optional<String> email) {
		if (name.isPresent()) {
			service.deleteUserByName(name.get());
		} else if (email.isPresent()) {
			service.deleteUserByemail(email.get());
		}
		return new ResponseEntity(HttpStatus.OK);
	}

	@GetMapping("/status")
	public String getStaus() {
		return "Application is up!";
	}
}
