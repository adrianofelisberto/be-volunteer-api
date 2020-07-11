package br.com.symplus.challenger.bevolunteer.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.symplus.challenger.bevolunteer.api.dtos.VolunteerDTO;
import br.com.symplus.challenger.bevolunteer.api.services.VolunteerService;

@RestController
@RequestMapping(path = "volunteers")
public class VolunterrController {

	@Autowired
	private VolunteerService service;
	
	
	@GetMapping
	public ResponseEntity<List<VolunteerDTO>> getAll() {
		return ResponseEntity.ok().body(service.getAll());
	}
	
	@GetMapping("{id}")
	public ResponseEntity<VolunteerDTO> getById(@PathVariable String id) {
		try {
			return ResponseEntity.ok(service.getById(Integer.valueOf(id)));
		} catch (Exception e) {
			return ResponseEntity.notFound().build();
		}
	}
	
}
