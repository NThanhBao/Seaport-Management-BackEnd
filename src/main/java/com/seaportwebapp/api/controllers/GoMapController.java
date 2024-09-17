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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.seaportwebapp.api.models.GoMap;
import com.seaportwebapp.api.models.ResponseObject;
import com.seaportwebapp.api.repositories.GoMapRepository;
import com.seaportwebapp.api.txt.Txt;

@RestController
@RequestMapping(path = "/api/sea/gomap")
public class GoMapController {
	@Autowired
	private GoMapRepository repository;

	@GetMapping
	public List<GoMap> getAllGoMaps() {
		return repository.findAll();
	}

	@GetMapping("/{id}")
	public ResponseEntity<ResponseObject> findByIdGoMap(@PathVariable Long id) {
		try {
			Optional<GoMap> foundGoMap = repository.findById(id);
			return foundGoMap.isPresent()
					? ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(Txt.txt[0], Txt.txt[2], foundGoMap))
					: ResponseEntity.status(HttpStatus.NOT_FOUND)
							.body(new ResponseObject(Txt.txt[1], Txt.txt[3] + id, null));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(new ResponseObject(Txt.txt[7], e.getMessage(), null));
		}
	}

	@PostMapping("/insert")
	public ResponseEntity<ResponseObject> insertGoMap(@RequestBody GoMap newGoMap) {
		try {
			return ResponseEntity.status(HttpStatus.OK)
					.body(new ResponseObject(Txt.txt[0], Txt.txt[2], repository.save(newGoMap)));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(new ResponseObject(Txt.txt[7], e.getMessage(), null));
		}
	}

//	@PutMapping("/{id}")
//	public ResponseEntity<ResponseObject> updateGoMap(@RequestBody GoMap newGoMap, @PathVariable Long id) {
//		try {
//			GoMap goMap = repository.findById(id).map(goMaps -> {
//				goMaps.setGoMapNameTPHCM(newGoMap.getGoMapNameTPHCM());
//				goMaps.setGoMapNameTNB(newGoMap.getGoMapNameTNB());
//				goMaps.setGoMapNameDNB(newGoMap.getGoMapNameDNB());
//				goMaps.setGoMapNameMTTN(newGoMap.getGoMapNameTPHCM());
//				return repository.save(goMaps);
//			}).orElseGet(() -> {
//				newGoMap.setId(id);
//				return repository.save(newGoMap);
//			});
//
//			return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(Txt.txt[0], Txt.txt[2], goMap));
//		} catch (Exception e) {
//			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
//					.body(new ResponseObject(Txt.txt[7], e.getMessage(), null));
//		}
//	}

	@DeleteMapping("/{id}")
	public ResponseEntity<ResponseObject> deleteGoMap(@PathVariable Long id) {
		boolean exists = repository.existsById(id);
		if (exists) {
			repository.deleteById(id);
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(Txt.txt[0], Txt.txt[2], null));
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body(new ResponseObject(Txt.txt[1], Txt.txt[3] + id, null));
		}
	}

}
