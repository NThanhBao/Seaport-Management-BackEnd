package com.seaportwebapp.api.controllers;

import java.util.List;
import java.util.Optional;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.seaportwebapp.api.models.ResponseObject;
import com.seaportwebapp.api.models.Ships;
import com.seaportwebapp.api.repositories.ShipsRepository;
import com.seaportwebapp.api.services.IStorageService;
import com.seaportwebapp.api.txt.Txt;

@RestController
@RequestMapping(path = "/api/sea/ships")
public class ShipsController {
	private final IStorageService storageService;

	@Autowired
	public ShipsController(IStorageService storageService) {
		this.storageService = storageService;
	}

	@Autowired
	private ShipsRepository repository;
		
	@GetMapping
	public List<Ships> getAllShips() {
		return repository.findAll();
	}

	@GetMapping("/{id}")
	public ResponseEntity<ResponseObject> findById(@PathVariable Long id) {
		try {
			Optional<Ships> foundShips = repository.findById(id);
			return foundShips.isPresent()
					? ResponseEntity.status(HttpStatus.OK)
							.body(new ResponseObject(Txt.txt[0], "Query ships successfully", foundShips.get()))
					: ResponseEntity.status(HttpStatus.NOT_FOUND)
							.body(new ResponseObject("Falsed", "Cannot find user with id = " + id, null));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(new ResponseObject("Error", e.getMessage(), null));
		}
	}

	@PostMapping("/insert")
	public ResponseEntity<ResponseObject> insertShips(@RequestBody Ships newShips) {
	    try {
	        List<Ships> foundShips = repository.findByShipName(newShips.getShipName().trim());
	        return foundShips.isEmpty()
	                ? ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("Ok", "Insert ships successfully", repository.save(newShips)))
	                : ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(new ResponseObject("False", "ShipsName already taken", null));
	    } catch (Exception e) {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseObject("Error", e.getMessage(), null));
	    }
	}


	@PutMapping("/{id}")
	public ResponseEntity<ResponseObject> updateShips(@RequestBody Ships newShips, @PathVariable Long id) {
		try {
			Ships ships = repository.findById(id).map(ship -> {
				ship.setShipName(newShips.getShipName());
				ship.setShipNameAuth(newShips.getShipNameAuth());
				ship.setShipNationality(newShips.getShipNationality());
				ship.setShipPlate(newShips.getShipPlate());
				ship.setShipSize(newShips.getShipSize());
				ship.setShipWeight(newShips.getShipWeight());
				ship.setShipWattage(newShips.getShipWattage());
				ship.setShipCheckIn(newShips.getShipCheckIn());
				ship.setShipAuthInfo(newShips.getShipAuthInfo());
				ship.setShipImage(newShips.getShipImage());
				return repository.save(ship);
			}).orElseGet(() -> {
				newShips.setId(id);
				return repository.save(newShips);
			});
			return ResponseEntity.status(HttpStatus.OK)
					.body(new ResponseObject("Success", "Ships updated successfully", ships));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(new ResponseObject("Error", e.getMessage(), null));
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<ResponseObject> deleteShips(@PathVariable Long id) {
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
