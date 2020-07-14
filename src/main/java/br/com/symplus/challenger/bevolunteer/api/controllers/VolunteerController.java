package br.com.symplus.challenger.bevolunteer.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.symplus.challenger.bevolunteer.api.dtos.MessageDTO;
import br.com.symplus.challenger.bevolunteer.api.dtos.VolunteerDTO;
import br.com.symplus.challenger.bevolunteer.api.services.VolunteerService;

@RestController
@RequestMapping(path = "volunteers")
public class VolunteerController {

	@Autowired
	private VolunteerService service;

	@GetMapping
	public ResponseEntity<List<VolunteerDTO>> getAll() {
		return ResponseEntity.ok().body(service.getAll());
	}

	@GetMapping("{id}")
	public ResponseEntity<VolunteerDTO> getById(@PathVariable Integer id) throws Exception {
		return ResponseEntity.ok(service.getById(id));
	}

	@PostMapping
	public ResponseEntity<MessageDTO> create(@RequestBody VolunteerDTO dto) throws Exception {
		return ResponseEntity.ok(service.create(dto));
	}

	@PutMapping
	public ResponseEntity<MessageDTO> update(@RequestBody VolunteerDTO dto) throws Exception {
		return ResponseEntity.ok(service.update(dto));
	}

	@DeleteMapping("{id}")
	public ResponseEntity<MessageDTO> remove(@PathVariable Integer id) throws Exception {
		return ResponseEntity.ok(service.removeById(id));
	}

}
