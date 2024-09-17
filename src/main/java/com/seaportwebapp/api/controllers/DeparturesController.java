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

import com.seaportwebapp.api.models.Departures;
import com.seaportwebapp.api.models.ResponseObject;
import com.seaportwebapp.api.repositories.DeparturesRepository;

@RestController
@RequestMapping(path = "/api/sea/departures")
public class DeparturesController {
	@Autowired
	private DeparturesRepository repository;

	@GetMapping
	public List<Departures> getAllDepartures() {
		return repository.findAll();
	}

	@PostMapping("/insert")
	public ResponseEntity<ResponseObject> insertArrivals(@RequestBody Departures newDeparture) {
		try {
			return ResponseEntity.status(HttpStatus.OK)
					.body(new ResponseObject("Ok", "Insert ships successfully", repository.save(newDeparture)));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(new ResponseObject("Error", e.getMessage(), null));
		}
	}
}
