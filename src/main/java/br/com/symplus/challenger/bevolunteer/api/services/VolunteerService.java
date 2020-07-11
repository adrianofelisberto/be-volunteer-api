package br.com.symplus.challenger.bevolunteer.api.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.symplus.challenger.bevolunteer.api.dtos.InterestDTO;
import br.com.symplus.challenger.bevolunteer.api.dtos.MessageDTO;
import br.com.symplus.challenger.bevolunteer.api.dtos.VolunteerDTO;
import br.com.symplus.challenger.bevolunteer.api.entities.Interest;
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

		List<InterestDTO> interests = optional.get().getInterests().stream().map(entity -> {
			InterestDTO interestDto = new InterestDTO();
			BeanUtils.copyProperties(entity, interestDto);
			return interestDto;
		}).collect(Collectors.toList());
		dto.setInterests(interests);

		return dto;

	}

	@Transactional(value = TxType.REQUIRED)
	public MessageDTO create(VolunteerDTO dto) throws Exception {
		Volunteer entity = new Volunteer();
		BeanUtils.copyProperties(dto, entity);
		
		List<Interest> interestsEnt = dto.getInterests().stream().map(interestDto -> {
			Interest interestEnt = new Interest();
			BeanUtils.copyProperties(interestDto, interestEnt);
			return interestEnt;
		}).collect(Collectors.toList());
		
		entity.setInterests(interestsEnt);
		repository.save(entity);

		return new MessageDTO("Voluntário salvo com sucesso.");
	}

}
