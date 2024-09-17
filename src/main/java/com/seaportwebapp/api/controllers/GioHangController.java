package com.seaportwebapp.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.seaportwebapp.api.models.GioHang;
import com.seaportwebapp.api.models.ResponseObject;
import com.seaportwebapp.api.repositories.GioHangRepository;

@RestController
@RequestMapping(path = "/api/sea/giohang")
public class GioHangController {
	@Autowired
	private GioHangRepository repository;

	@GetMapping
	public List<GioHang> getAllGioHang() {
		return repository.findAll();
	}

	@PostMapping("/insert")
	public ResponseEntity<ResponseObject> insertGioHang(@RequestBody GioHang newCart) {
		try {
			return ResponseEntity.status(HttpStatus.OK)
					.body(new ResponseObject("Ok", "Insert ships successfully", repository.save(newCart)));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(new ResponseObject("Error", e.getMessage(), null));
		}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<ResponseObject> deleteGioHang(@PathVariable Long id) {
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
