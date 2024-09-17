package com.seaportwebapp.api.controllers;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.seaportwebapp.api.models.AuthUser;
import com.seaportwebapp.api.models.ResponseObject;
import com.seaportwebapp.api.repositories.AuthUserRepository;

@RestController
@RequestMapping(path = "/api/sea/authuser")
public class AuthUserController {
	@Autowired
	private AuthUserRepository repository;

	@GetMapping
	public List<AuthUser> getAllAuthUsers() {
		return repository.findAll();
	}
	
	@PostMapping("/insert")
	public ResponseEntity<ResponseObject> insertGoods(@RequestBody AuthUser newA) {
		try {
			return ResponseEntity.status(HttpStatus.OK)
							.body(new ResponseObject("Ok", "Insert ships successfully", repository.save(newA)));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(new ResponseObject("Error", e.getMessage(), null));
		}
	}
}
