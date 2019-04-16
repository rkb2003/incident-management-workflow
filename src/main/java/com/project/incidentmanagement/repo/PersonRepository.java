package com.project.incidentmanagement.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.project.incidentmanagement.model.Person;

//@Service
public interface PersonRepository extends JpaRepository<Person, Long> {

	Person findByName(String name);

}
