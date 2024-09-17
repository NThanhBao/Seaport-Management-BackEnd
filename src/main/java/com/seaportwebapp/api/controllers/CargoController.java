package com.seaportwebapp.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.seaportwebapp.api.models.Cargo;
import com.seaportwebapp.api.models.ResponseObject;
import com.seaportwebapp.api.repositories.CargoRepository;
import com.seaportwebapp.api.txt.Txt;

@RestController
@RequestMapping(path = "/api/sea/cargo")
public class CargoController {
	@Autowired
	private CargoRepository repository;

	@GetMapping
	public List<Cargo> getAllCargos() {
		return repository.findAll();
	}

	@PostMapping("/insert")
	public ResponseEntity<ResponseObject> insertCargo(@RequestBody Cargo newCargo) {
		try {
			return ResponseEntity.status(HttpStatus.OK)
					.body(new ResponseObject(Txt.txt[0], Txt.txt[2], repository.save(newCargo)));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(new ResponseObject("Error", e.getMessage(), null));
		}
	}
}
