package br.com.symplus.challenger.bevolunteer.api.dtos;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class VolunteerDTO {

	private Integer id;
	private String name;
	private String email;
	private String phone;
	private String whatsapp;
	private List<InterestDTO> interests;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getWhatsapp() {
		return whatsapp;
	}

	public void setWhatsapp(String whatsapp) {
		this.whatsapp = whatsapp;
	}

	public List<InterestDTO> getInterests() {
		return interests;
	}

	public void setInterests(List<InterestDTO> interests) {
		this.interests = interests;
	}

}
