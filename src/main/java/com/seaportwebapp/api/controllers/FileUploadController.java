package com.seaportwebapp.api.controllers;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.seaportwebapp.api.models.Images;
import com.seaportwebapp.api.models.ResponseObject;
import com.seaportwebapp.api.repositories.ImagesRepository;
import com.seaportwebapp.api.repositories.ShipsRepository;
import com.seaportwebapp.api.services.IStorageService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping(path = "api/sea/fileupload")
public class FileUploadController {
	private final IStorageService storageService;

	@Autowired
	private ImagesRepository repository;

	@Autowired
	public FileUploadController(IStorageService storageService) {
		this.storageService = storageService;
	}

	@PostMapping
	public ResponseEntity<ResponseObject> uploadFile(@RequestParam("file") MultipartFile file) {
		if (file != null && !file.isEmpty()) {
			try {
				String generatedFileName = storageService.storeFile(file);
				Images image = new Images(generatedFileName); // Tạo đối tượng Images với đường dẫn ảnh
				repository.save(image); // Lưu đối tượng Images vào cơ sở dữ liệu
				return ResponseEntity.status(HttpStatus.OK)
						.body(new ResponseObject("Ok", "Upload file successfully", generatedFileName));
			} catch (Exception e) {
				return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("Failed", e.getMessage(), null));
			}
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(new ResponseObject("Failed", "No file found in the request", null));
		}
	}

	@GetMapping("/files/{fileName:.+}")
	public ResponseEntity<byte[]> readDetailFile(@PathVariable String fileName) {
		try {
			byte[] bytes = storageService.readFileContent(fileName);
			return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(bytes);
		} catch (Exception e) {
			return ResponseEntity.noContent().build();
		}
	}

}
