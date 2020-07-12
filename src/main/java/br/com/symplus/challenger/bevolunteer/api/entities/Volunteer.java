package br.com.symplus.challenger.bevolunteer.api.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

@Entity(name = "volunteers")
public class Volunteer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "name", length = 45, nullable = false)
	private String name;

	@Column(name = "email", length = 45, nullable = false)
	private String email;

	@Column(name = "phone", length = 45, nullable = false)
	private String phone;

	@Column(name = "whatsapp", length = 45, nullable = true)
	private String whatsapp;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "volunteers_has_interests", joinColumns = {
			@JoinColumn(name = "volunteers_id") }, inverseJoinColumns = { @JoinColumn(name = "interests_id") })
	private List<Interest> interests;

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

	public List<Interest> getInterests() {
		return interests;
	}

	public void setInterests(List<Interest> interests) {
		this.interests = interests;
	}

}
