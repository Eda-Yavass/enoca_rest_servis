package com.edayavas.enoca_rest_servis.repository;

import com.edayavas.enoca_rest_servis.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
    Optional<Person> findPersonById(Long id);
}
