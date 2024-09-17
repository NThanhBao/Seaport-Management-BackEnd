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
import org.springframework.web.bind.annotation.RestController;

import com.seaportwebapp.api.models.Goods;
import com.seaportwebapp.api.models.ResponseObject;
import com.seaportwebapp.api.repositories.GoodsRepository;

@RestController
@RequestMapping(path = "/api/sea/goods")
public class GoodsController {
	@Autowired
	private GoodsRepository repository;

	@GetMapping
	public List<Goods> getAllGoods() {
		return repository.findAll();
	}

	@GetMapping("/{id}")
	public ResponseEntity<ResponseObject> findById(@PathVariable Long id) {
		try {
			Optional<Goods> foundGoods = repository.findById(id);
			return foundGoods.isPresent()
					? ResponseEntity.status(HttpStatus.OK)
							.body(new ResponseObject("Ok", "Query ships successfully", foundGoods.get()))
					: ResponseEntity.status(HttpStatus.NOT_FOUND)
							.body(new ResponseObject("Falsed", "Cannot find user with id = " + id, null));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(new ResponseObject("Error", e.getMessage(), null));
		}
	}

	@PostMapping("/insert")
	public ResponseEntity<ResponseObject> insertGoods(@RequestBody Goods newGoods) {
		try {
			List<Goods> foundGoods = repository.findByGoodsName(newGoods.getGoodsName().trim());
			return foundGoods.isEmpty()
					? ResponseEntity.status(HttpStatus.OK)
							.body(new ResponseObject("Ok", "Insert ships successfully", repository.save(newGoods)))
					: ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED)
							.body(new ResponseObject("False", "ShipsName already taken", null));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(new ResponseObject("Error", e.getMessage(), null));
		}
	}

	@PutMapping("/{id}")
	public ResponseEntity<ResponseObject> updateGoods(@RequestBody Goods newGoods, @PathVariable Long id) {
		try {
			Goods goods = repository.findById(id).map(good -> {
				good.setGoodsName(newGoods.getGoodsName());
				return repository.save(good);
			}).orElseGet(() -> {
				newGoods.setId(id);
				return repository.save(newGoods);
			});
			return ResponseEntity.status(HttpStatus.OK)
					.body(new ResponseObject("Success", "Ships updated successfully", goods));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(new ResponseObject("Error", e.getMessage(), null));
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<ResponseObject> deleteGoods(@PathVariable Long id) {
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
