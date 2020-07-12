package br.com.symplus.challenger.bevolunteer.api.services;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.symplus.challenger.bevolunteer.api.config.MessageComponent;
import br.com.symplus.challenger.bevolunteer.api.dtos.InterestDTO;
import br.com.symplus.challenger.bevolunteer.api.dtos.MessageDTO;
import br.com.symplus.challenger.bevolunteer.api.dtos.VolunteerDTO;
import br.com.symplus.challenger.bevolunteer.api.entities.Interest;
import br.com.symplus.challenger.bevolunteer.api.entities.Volunteer;
import br.com.symplus.challenger.bevolunteer.api.enuns.MessageCode;
import br.com.symplus.challenger.bevolunteer.api.handlerError.models.InvalidIdExeception;
import br.com.symplus.challenger.bevolunteer.api.repositories.VolunteerRepository;
import javassist.NotFoundException;

@Service
public class VolunteerService {

	@Autowired
	private VolunteerRepository repository;

	public List<VolunteerDTO> getAll() {
		List<Volunteer> volunteers = repository.findAll();

		return volunteers.stream().map(entity -> {
			VolunteerDTO dto = new VolunteerDTO();
			BeanUtils.copyProperties(entity, dto);
			// TODO: remove when solving problem with Lazy
			dto.setInterests(null);
			return dto;
		}).collect(Collectors.toList());
	}

	public VolunteerDTO getById(Integer id) throws NotFoundException {
		Optional<Volunteer> optional = repository.findById(id);

		if (!optional.isPresent()) {
			throw new NotFoundException(
				MessageComponent.getMessage(MessageCode.NOT_FOUND.getCode(), "Voluntário")
			);
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
	
	public void removeById(Integer id) throws Exception {
		isValidVolunteer(id);
		repository.deleteById(id);
	}

	@Transactional(value = TxType.REQUIRED)
	public MessageDTO create(VolunteerDTO dto) throws Exception {
		saveVolunteer(dto);
		return new MessageDTO("Voluntário salvo com sucesso.");
	}

	@Transactional(value = TxType.REQUIRED)
	public MessageDTO update(VolunteerDTO dto) throws Exception {
		isValidVolunteer(dto.getId());
		saveVolunteer(dto);
		return new MessageDTO("Voluntário editado com sucesso.");
	}

	private void saveVolunteer(VolunteerDTO dto) {
		Volunteer entity = this.convertDtoToEntity(dto);
		List<Interest> interestsEnt = this.convertInterestsDtoToEntity(dto.getInterests());

		entity.setInterests(interestsEnt);
		repository.save(entity);
	}

	private Volunteer convertDtoToEntity(VolunteerDTO dto) {
		Volunteer entity = new Volunteer();
		BeanUtils.copyProperties(dto, entity);
		return entity;
	}

	private List<Interest> convertInterestsDtoToEntity(List<InterestDTO> interestsDto) {
		return interestsDto.stream().map(interestDto -> {
			Interest interestEnt = new Interest();
			BeanUtils.copyProperties(interestDto, interestEnt);
			return interestEnt;
		}).collect(Collectors.toList());
	}
	
	private void isValidVolunteer(Integer id) throws NotFoundException, InvalidIdExeception {
		
		if (Objects.isNull(id)) {
			throw new InvalidIdExeception();
		}
		
		Optional<Volunteer> optional = repository.findById(id);

		if (!optional.isPresent()) {
			throw new NotFoundException(
				MessageComponent.getMessage(MessageCode.NOT_FOUND.getCode(), "Voluntário")
			);
		}
	}

}
