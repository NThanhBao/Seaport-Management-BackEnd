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

import com.seaportwebapp.api.models.Arrivals;
import com.seaportwebapp.api.models.Berhts;
import com.seaportwebapp.api.models.ResponseObject;
import com.seaportwebapp.api.repositories.ArrivalsRepository;

@RestController
@RequestMapping(path = "/api/sea/arrivals")
public class ArrivalsController {
	@Autowired
	private ArrivalsRepository repository;

	@GetMapping
	public List<Arrivals> getAllArrivals() {
		return repository.findAll();
	}

	@PostMapping("/insert")
	public ResponseEntity<ResponseObject> insertArrivals(@RequestBody Arrivals newArrival){
		try {
	        return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("Ok", "Insert ships successfully", repository.save(newArrival)));
	    } catch (Exception e) {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseObject("Error", e.getMessage(), null));
	    }
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<ResponseObject> updateArrival(@RequestBody Arrivals newArrival, @PathVariable Long id) {
		try {
			Arrivals arrivals = repository.findById(id).map(arrival -> {
				arrival.setShipId(newArrival.getShipId());
				arrival.setBerthId(newArrival.getBerthId());
				arrival.setArrivalDate(newArrival.getArrivalDate());
				arrival.setArrivalTime(newArrival.getArrivalTime());
				return repository.save(arrival);
			}).orElseGet(() -> {
				newArrival.setId(id);
				return repository.save(newArrival);
			});
			return ResponseEntity.status(HttpStatus.OK)
					.body(new ResponseObject("Success", "Ships updated successfully", arrivals));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(new ResponseObject("Error", e.getMessage(), null));
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<ResponseObject> deleteArrival(@PathVariable Long id) {
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
