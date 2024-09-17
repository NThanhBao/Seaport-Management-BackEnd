package com.seaportwebapp.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.seaportwebapp.api.models.ResponseObject;
import com.seaportwebapp.api.models.Ships;
import com.seaportwebapp.api.models.Users;
import com.seaportwebapp.api.repositories.UsersRepository;

@RestController
@RequestMapping(path = "/api/sea/users")
public class UsersController {
	@Autowired
	private UsersRepository repository;

	@GetMapping
	public List<Users> getAllUsers() {
		return repository.findAll();
	}

	@PostMapping("/insert")
	public ResponseEntity<ResponseObject> insertUser(@RequestBody Users newUser) {
		try {
			return ResponseEntity.status(HttpStatus.OK)
					.body(new ResponseObject("Ok", "Insert ships successfully", repository.save(newUser)));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(new ResponseObject("Error", e.getMessage(), null));
		}
	}

	@PutMapping("/{id}")
	public ResponseEntity<ResponseObject> updateUser(@RequestBody Users newUser, @PathVariable Long id) {
		try {
			Users users = repository.findById(id).map(user -> {
				user.setFirstName(newUser.getFirstName());
				user.setLastName(newUser.getLastName());
				user.setEmail(newUser.getEmail());
				user.setPhone(newUser.getPhone());
				user.setAddress(newUser.getAddress());
				user.setImage(newUser.getImage());
				return repository.save(user);
			}).orElseGet(() -> {
				newUser.setId(id);
				return repository.save(newUser);
			});
			return ResponseEntity.status(HttpStatus.OK)
					.body(new ResponseObject("Success", "Ships updated successfully", users));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(new ResponseObject("Error", e.getMessage(), null));
		}
	}
}
