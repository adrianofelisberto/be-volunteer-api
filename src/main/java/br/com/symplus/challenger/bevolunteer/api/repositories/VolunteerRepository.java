package br.com.symplus.challenger.bevolunteer.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.symplus.challenger.bevolunteer.api.entities.Volunteer;

@Repository
public interface VolunteerRepository extends JpaRepository<Volunteer, Integer> {

}
