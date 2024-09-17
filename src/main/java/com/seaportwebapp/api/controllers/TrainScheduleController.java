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

import com.seaportwebapp.api.models.ResponseObject;
import com.seaportwebapp.api.models.TrainSchedule;
import com.seaportwebapp.api.repositories.TrainScheduleRepository;
import com.seaportwebapp.api.txt.Txt;

@RestController
@RequestMapping(path = "/api/sea/trainschedule")
public class TrainScheduleController {
	@Autowired
	private TrainScheduleRepository repository;

	@GetMapping
	public List<TrainSchedule> getAllTrainSchedules() {
		return repository.findAll();
	}

	@GetMapping("/{id}")
	public ResponseEntity<ResponseObject> findById(@PathVariable Long id) {
		try {
			Optional<TrainSchedule> foundTrainSchedule = repository.findById(id);
			return foundTrainSchedule.isPresent()
					? ResponseEntity.status(HttpStatus.OK)
							.body(new ResponseObject(Txt.txt[0], Txt.txt[2], foundTrainSchedule))
					: ResponseEntity.status(HttpStatus.NOT_FOUND)
							.body(new ResponseObject(Txt.txt[1], Txt.txt[3] + id, null));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(new ResponseObject(Txt.txt[7], e.getMessage(), null));
		}
	}

	@PostMapping("/insert")
	public ResponseEntity<ResponseObject> insertTrainSchedule(@RequestBody TrainSchedule newTrainSchedule) {
		try {
			return ResponseEntity.status(HttpStatus.OK)
					.body(new ResponseObject(Txt.txt[0], Txt.txt[2], repository.save(newTrainSchedule)));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(new ResponseObject(Txt.txt[7], e.getMessage(), null));
		}
	}

	@PutMapping("/{id}")
	public ResponseEntity<ResponseObject> updateTrainSchedule(@RequestBody TrainSchedule newTrainSchedule,
			@PathVariable Long id) {
		try {
			TrainSchedule train = repository.findById(id).map(trains -> {
				trains.setTimeIn(newTrainSchedule.getTimeIn());
				trains.setTimeOut(newTrainSchedule.getTimeOut());
				trains.setDestinationPort(newTrainSchedule.getDestinationPort());
				trains.setDeparturePort(newTrainSchedule.getDeparturePort());
				trains.setShipsId(newTrainSchedule.getShipsId());
				return repository.save(trains);
			}).orElseGet(() -> {
				newTrainSchedule.setId(id);
				return repository.save(newTrainSchedule);
			});
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(Txt.txt[0], Txt.txt[2], train));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(new ResponseObject(Txt.txt[7], e.getMessage(), null));
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<ResponseObject> deleteTrainSchedule(@PathVariable Long id) {
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
