package br.com.symplus.challenger.bevolunteer.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.symplus.challenger.bevolunteer.api.dtos.InterestDTO;
import br.com.symplus.challenger.bevolunteer.api.services.InterestService;

@RestController
@RequestMapping(path = "interests")
public class InterestController {
	
	@Autowired
	private InterestService service;
	
	@GetMapping
	public ResponseEntity<List<InterestDTO>> getAll() {
		return ResponseEntity.ok().body(service.getAll());
	}

}
