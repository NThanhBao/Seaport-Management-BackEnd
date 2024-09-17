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

import com.seaportwebapp.api.models.Harbor;
import com.seaportwebapp.api.models.ResponseObject;
import com.seaportwebapp.api.repositories.HarborRepository;
import com.seaportwebapp.api.txt.Txt;

@RestController
@RequestMapping(path = "/api/sea/harbor")
public class HarborController {
	@Autowired
	private HarborRepository repository;

	@GetMapping
	public List<Harbor> getAllHarbors() {
		return repository.findAll();
	}

	@GetMapping("/{id}")
	public ResponseEntity<ResponseObject> findById(@PathVariable Long id) {
		try {
			Optional<Harbor> foundHarbor = repository.findById(id);
			return foundHarbor.isPresent()
					? ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(Txt.txt[0], Txt.txt[2], foundHarbor))
					: ResponseEntity.status(HttpStatus.NOT_FOUND)
							.body(new ResponseObject(Txt.txt[1], Txt.txt[3] + id, null));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(new ResponseObject(Txt.txt[7], e.getMessage(), null));
		}
	}

	@PostMapping("/insert")
	public ResponseEntity<ResponseObject> insertHarbor(@RequestBody Harbor newHarbor) {
		try {
			return ResponseEntity.status(HttpStatus.OK)
					.body(new ResponseObject(Txt.txt[0], Txt.txt[2], repository.save(newHarbor)));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(new ResponseObject(Txt.txt[7], e.getMessage(), null));
		}
	}

	@PutMapping("/{id}")
	public ResponseEntity<ResponseObject> updateHarbor(@RequestBody Harbor newHarbor, @PathVariable Long id) {
		try {
			Harbor harbor = repository.findById(id).map(harbors -> {
				harbors.setStatus(newHarbor.getStatus());
				harbors.setTime(newHarbor.getTime());
				harbors.setAbility(newHarbor.getAbility());
				return repository.save(harbors);
			}).orElseGet(() -> {
				newHarbor.setId(id);
				return repository.save(newHarbor);
			});
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(Txt.txt[0], Txt.txt[2], harbor));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(new ResponseObject(Txt.txt[7], e.getMessage(), null));
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<ResponseObject> deleteHarbor(@PathVariable Long id) {
		try {
			boolean exists = repository.existsById(id);
			if (exists) {
				repository.deleteById(id);
				return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(Txt.txt[0], Txt.txt[2], null));
			} else {
				return ResponseEntity.status(HttpStatus.NOT_FOUND)
						.body(new ResponseObject(Txt.txt[1], Txt.txt[3] + id, null));
			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(new ResponseObject(Txt.txt[7], e.getMessage(), null));
		}
	}
}
