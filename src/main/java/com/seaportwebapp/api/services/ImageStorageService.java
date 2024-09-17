package com.seaportwebapp.api.services;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Arrays;
import java.util.UUID;
import java.util.stream.Stream;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ImageStorageService implements IStorageService {
	private final Path storageFolder = Paths.get("uploads");

	public ImageStorageService() {
		try {
			Files.createDirectories(storageFolder);
		} catch (Exception e) {
			throw new RuntimeException("Cannot initialize storage", e);
		}
	}

	private boolean isImageFile(MultipartFile file) {
		String fileExtension = FilenameUtils.getExtension(file.getOriginalFilename());
		return Arrays.asList(new String[] { "png", "jpg", "jpeg", "bmp" }).contains(fileExtension.trim().toLowerCase());
	}

	@Override
	public String storeFile(MultipartFile file) {
	    try {
	        System.out.println("Sea");
	        if (file.isEmpty()) {
	            throw new RuntimeException("Failed to store empty file");
	        }
	        if (!isImageFile(file)) {
	            throw new RuntimeException("You can only upload image file");
	        }
	        float fileSizeInMegabytes = file.getSize() / 1_000_000.0f;
	        if (fileSizeInMegabytes > 5.0f) {
	            throw new RuntimeException("File must be <= 5Mb");
	        }
	        String fileExtension = FilenameUtils.getExtension(file.getOriginalFilename());
	        String originalFileName = FilenameUtils.getBaseName(file.getOriginalFilename());
	        String generateFileName = originalFileName + "." + fileExtension;
	        Path destinationFilePath = this.storageFolder.resolve(Paths.get(generateFileName)).normalize().toAbsolutePath();
	        try (InputStream inputStream = file.getInputStream()) {
	            Files.copy(inputStream, destinationFilePath, StandardCopyOption.REPLACE_EXISTING);
	        }
	        return generateFileName;
	    } catch (Exception e) {
	        throw new RuntimeException("Failed to store empty file");
	    }
	}

	@Override
	public Stream<Path> loadAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public byte[] readFileContent(String fileName) {
		try {
			Path file = storageFolder.resolve(fileName);
			Resource resource = new UrlResource(file.toUri());

			if (resource.exists() || resource.isReadable()) {
				byte[] bytes = IOUtils.toByteArray(resource.getInputStream());
				return bytes;
			} else {
				throw new RuntimeException("Could not read file: " + fileName);
			}
		} catch (Exception e) {
			throw new RuntimeException("Failed to read file: " + fileName, e);
		}
	}

	@Override
	public void deleteAllFiles() {
		// TODO Auto-generated method stub

	}

}
