package com.seaportwebapp.api.controllers;

import java.util.List;

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

import com.seaportwebapp.api.models.Berhts;
import com.seaportwebapp.api.models.ResponseObject;
import com.seaportwebapp.api.repositories.BerthsRepository;

@RestController
@RequestMapping(path = "/api/sea/berths")
public class BerhtsController {
	@Autowired
	private BerthsRepository repository;

	@GetMapping
	public List<Berhts> getAllBerths() {
		return repository.findAll();
	}

	@PostMapping("/insert")
	public ResponseEntity<ResponseObject> insertBerth(@RequestBody Berhts newBerth) {
		try {
			List<Berhts> foundBerths = repository.findByBerthName(newBerth.getBerthName().trim());
			return foundBerths.isEmpty()
					? ResponseEntity.status(HttpStatus.OK)
							.body(new ResponseObject("Ok", "Insert ships successfully", repository.save(newBerth)))
					: ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED)
							.body(new ResponseObject("False", "ShipsName already taken", null));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(new ResponseObject("Error", e.getMessage(), null));
		}
	}

	@PutMapping("/{id}")
	public ResponseEntity<ResponseObject> updateBerth(@RequestBody Berhts newBerth, @PathVariable Long id) {
		try {
			Berhts berths = repository.findById(id).map(berth -> {
				berth.setBerthName(newBerth.getBerthName());
				berth.setBerthCapacity(newBerth.getBerthCapacity());
				berth.setBerthStatus(newBerth.getBerthStatus());
				return repository.save(berth);
			}).orElseGet(() -> {
				newBerth.setId(id);
				return repository.save(newBerth);
			});
			return ResponseEntity.status(HttpStatus.OK)
					.body(new ResponseObject("Success", "Ships updated successfully", berths));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(new ResponseObject("Error", e.getMessage(), null));
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<ResponseObject> deleteBerth(@PathVariable Long id) {
		try {
			boolean exists = repository.existsById(id);
			if (exists) {
				repository.deleteById(id);
				return ResponseEntity.status(HttpStatus.OK)
						.body(new ResponseObject("Success", "Ships delete successfully", null));
			}
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body(new ResponseObject("Falsed", "Ships deleted", null));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(new ResponseObject("Error", e.getMessage(), null));
		}
	}
}
