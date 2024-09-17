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

import com.seaportwebapp.api.models.ResponseObject;
import com.seaportwebapp.api.models.SanPham;
import com.seaportwebapp.api.repositories.SanPhamRepository;

@RestController
@RequestMapping(path = "/api/sea/sanpham")
public class SanPhamController {
	@Autowired
	private SanPhamRepository repository;

	@GetMapping
	public List<SanPham> getAllSanPhams() {
		return repository.findAll();
	}

	@PostMapping("/insert")
	public ResponseEntity<ResponseObject> insertSanPham(@RequestBody SanPham newSanPham) {
		try {
			return ResponseEntity.status(HttpStatus.OK)
					.body(new ResponseObject("Ok", "Insert ships successfully", repository.save(newSanPham)));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(new ResponseObject("Error", e.getMessage(), null));
		}
	}

	@PutMapping("/{id}")
	public ResponseEntity<ResponseObject> updateSanPham(@RequestBody SanPham newSanPham, @PathVariable Long id) {
		try {
			SanPham sanPhams = repository.findById(id).map(sanpham -> {
				sanpham.setPhanLoaiId(newSanPham.getPhanLoaiId());
				sanpham.setTenSanPham(newSanPham.getTenSanPham());
				sanpham.setMoTaSanPham(newSanPham.getMoTaSanPham());
				sanpham.setGiaBan(newSanPham.getGiaBan());
				sanpham.setNgayThemVao(newSanPham.getNgayThemVao());
				sanpham.setHinhSanPham(newSanPham.getHinhSanPham());
				return repository.save(sanpham);
			}).orElseGet(() -> {
				newSanPham.setId(id);
				return repository.save(newSanPham);
			});
			return ResponseEntity.status(HttpStatus.OK)
					.body(new ResponseObject("Success", "Ships updated successfully", sanPhams));
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
