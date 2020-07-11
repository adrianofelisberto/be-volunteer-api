package br.com.symplus.challenger.bevolunteer.api.services;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.symplus.challenger.bevolunteer.api.dtos.InterestDTO;
import br.com.symplus.challenger.bevolunteer.api.dtos.VolunteerDTO;
import br.com.symplus.challenger.bevolunteer.api.entities.Volunteer;
import br.com.symplus.challenger.bevolunteer.api.repositories.VolunteerRepository;

@Service
public class VolunteerService {

	@Autowired
	private VolunteerRepository repository;
	
	public List<VolunteerDTO> getAll() {
		List<Volunteer> volunteers = repository.findAll();
		
		return volunteers.stream().map(entity -> {
			VolunteerDTO dto = new VolunteerDTO();
			BeanUtils.copyProperties(entity, dto);
			return dto;
		}).collect(Collectors.toList());
	}
	
	public VolunteerDTO getById(Integer id) throws Exception {
		Optional<Volunteer> optional = repository.findById(id);
		
		if (!optional.isPresent()) {
			throw new Exception("Not found");
		}
		
		VolunteerDTO dto = new VolunteerDTO();
		BeanUtils.copyProperties(optional.get(), dto);
		
		dto.setInterests(Arrays.asList(new InterestDTO()));
		
		return dto;
		
	}
	
}
