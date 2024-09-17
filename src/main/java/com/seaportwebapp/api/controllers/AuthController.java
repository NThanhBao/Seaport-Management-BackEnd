package com.seaportwebapp.api.controllers;

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

import com.seaportwebapp.api.models.ResponseObject;
import com.seaportwebapp.api.models.UserInfo;
import com.seaportwebapp.api.repositories.UserRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api/sea/auth")
public class AuthController {
	@Autowired
	private UserRepository repository;

	@GetMapping
	public List<UserInfo> getAllUsers() {
		return repository.findAll();
	}

	@GetMapping("/{id}")
	// Get user
	public ResponseEntity<ResponseObject> findById(@PathVariable Long id) {
		// Optional trả về giá trị có có thể là null;
		Optional<UserInfo> foundUser = repository.findById(id);
		return foundUser.isPresent()
				? ResponseEntity.status(HttpStatus.OK)
						.body(new ResponseObject("Ok", "Query user successfully", foundUser))
				: ResponseEntity.status(HttpStatus.NOT_FOUND)
						.body(new ResponseObject("Falsed", "Cannot find user with id = " + id, foundUser));
	}

	@PostMapping("/insert")
	// Insert user
	public ResponseEntity<ResponseObject> insertUser(@RequestBody UserInfo newUser) {
		List<UserInfo> foundUsers = repository.findByUsername(newUser.getUsername().trim());
		return foundUsers.size() > 0
				? ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED)
						.body(new ResponseObject("Falsed", "Username already taken", null))
				: ResponseEntity.status(HttpStatus.OK)
						.body(new ResponseObject("Ok", "Insert user successfully", repository.save(newUser)));
	}

	@PutMapping("/{id}")
	// Upsert user
	public ResponseEntity<ResponseObject> updateUser(@RequestBody UserInfo newUser, @PathVariable Long id) {
		UserInfo updatedUser = repository.findById(id).map(users -> {
			users.setUsername(newUser.getUsername());
			users.setPassword(newUser.getPassword());
			users.setEmail(newUser.getEmail());
			users.setPhone(newUser.getPhone());
			users.setAddress(newUser.getAddress());
			return repository.save(users);
		}).orElseGet(() -> {
			newUser.setId(id);
			return repository.save(newUser);
		});
		return ResponseEntity.status(HttpStatus.OK)
				.body(new ResponseObject("Success", "User updated successfully", updatedUser));
	}

	@DeleteMapping("/{id}")
	// Delete user
	public ResponseEntity<ResponseObject> deleteUser(@PathVariable Long id) {
		boolean exists = repository.existsById(id);
		if (exists) {
			repository.deleteById(id);
			return ResponseEntity.status(HttpStatus.OK)
					.body(new ResponseObject("Ok", "Delete user successfully", null));
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseObject("Falsed", "User deleted", null));
		}
	}

}
