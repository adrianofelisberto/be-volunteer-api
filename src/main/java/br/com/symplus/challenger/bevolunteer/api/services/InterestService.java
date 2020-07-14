package br.com.symplus.challenger.bevolunteer.api.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.symplus.challenger.bevolunteer.api.dtos.InterestDTO;
import br.com.symplus.challenger.bevolunteer.api.entities.Interest;
import br.com.symplus.challenger.bevolunteer.api.repositories.InterestRepository;

@Service
public class InterestService {

	@Autowired
	private InterestRepository repository;

	public List<InterestDTO> getAll() {
		List<Interest> entities = repository.findAll();

		return entities.stream().map(entity -> {
			InterestDTO dto = new InterestDTO();
			BeanUtils.copyProperties(entity, dto);
			return dto;
		}).collect(Collectors.toList());
	}

}
