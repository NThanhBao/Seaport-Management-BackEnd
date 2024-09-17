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

import com.seaportwebapp.api.models.PhanLoai;
import com.seaportwebapp.api.models.ResponseObject;
import com.seaportwebapp.api.repositories.PhanLoaiRepository;

@RestController
@RequestMapping(path = "/api/sea/phanloai")
public class PhanLoaiController {
	@Autowired
	private PhanLoaiRepository repository;

	@GetMapping
	public List<PhanLoai> getAllPhanLoai() {
		return repository.findAll();
	}

	@PostMapping("/insert")
	public ResponseEntity<ResponseObject> insertPhanLoai(@RequestBody PhanLoai newPhanLoai) {
		try {
			return ResponseEntity.status(HttpStatus.OK)
					.body(new ResponseObject("Ok", "Insert ships successfully", repository.save(newPhanLoai)));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(new ResponseObject("Error", e.getMessage(), null));
		}
	}
}
