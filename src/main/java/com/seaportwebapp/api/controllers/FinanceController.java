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

import com.seaportwebapp.api.models.Finance;
import com.seaportwebapp.api.models.ResponseObject;
import com.seaportwebapp.api.repositories.FinanceRepository;
import com.seaportwebapp.api.txt.Txt;

@RestController
@RequestMapping(path = "/api/sea/finance")
public class FinanceController {
	@Autowired
	private FinanceRepository repository;

	@GetMapping
	public List<Finance> getAllFinances() {
		return repository.findAll();
	}

	@GetMapping("/{id}")
	public ResponseEntity<ResponseObject> findById(@PathVariable Long id) {
		try {
			Optional<Finance> foundFinance = repository.findById(id);
			return foundFinance.isPresent()
					? ResponseEntity.status(HttpStatus.OK)
							.body(new ResponseObject(Txt.txt[0], Txt.txt[2], foundFinance))
					: ResponseEntity.status(HttpStatus.NOT_FOUND)
							.body(new ResponseObject(Txt.txt[1], Txt.txt[3] + id, null));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(new ResponseObject(Txt.txt[7], e.getMessage(), null));
		}
	}

	@PostMapping("/insert")
	public ResponseEntity<ResponseObject> insertFinance(@RequestBody Finance newFinance) {
		try {
			return ResponseEntity.status(HttpStatus.OK)
					.body(new ResponseObject(Txt.txt[0], Txt.txt[2], repository.save(newFinance)));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(new ResponseObject(Txt.txt[7], e.getMessage(), null));
		}
	}

	@PutMapping("/{id}")
	public ResponseEntity<ResponseObject> updateFinance(@RequestBody Finance newFinance, @PathVariable Long id) {
		try {
			Finance finance = repository.findById(id).map(finances -> {
				finances.setBill(newFinance.getBill());
				finances.setPay(newFinance.getPay());
				finances.setMovinContract(newFinance.getMovinContract());
				finances.setPortFee(newFinance.getPortFee());
				return repository.save(finances);
			}).orElseGet(() -> {
				newFinance.setId(id);
				return repository.save(newFinance);
			});
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(Txt.txt[0], Txt.txt[2], finance));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(new ResponseObject(Txt.txt[7], e.getMessage(), null));
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<ResponseObject> deleteFinance(@PathVariable Long id) {
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
