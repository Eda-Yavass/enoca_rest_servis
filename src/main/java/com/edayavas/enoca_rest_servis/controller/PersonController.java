package com.edayavas.enoca_rest_servis.controller;

import com.edayavas.enoca_rest_servis.entity.Adres;
import com.edayavas.enoca_rest_servis.entity.Person;
import com.edayavas.enoca_rest_servis.exception.ResourceNotFoundException;
import com.edayavas.enoca_rest_servis.repository.AdresRepository;
import com.edayavas.enoca_rest_servis.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api/v1/person")
public class PersonController {

    private final PersonRepository personRepository;
    private final AdresRepository adresRepository;

    @Autowired
    public PersonController(PersonRepository personRepository, AdresRepository adresRepository) {
        this.personRepository = personRepository;
        this.adresRepository = adresRepository;
    }

    @GetMapping
    public ResponseEntity<List<Person>> getAllRecords() {
        List<Person> personList = personRepository.findAll();
        return ResponseEntity.ok(personList);
    }

    @GetMapping("{personId}")
    public ResponseEntity<Person> getPersonById(@PathVariable Long personId) {
        Optional<Person> optionalPerson = personRepository.findPersonById(personId);
        if (!optionalPerson.isPresent()){
            throw new ResourceNotFoundException("Person is not found");
        }else {
            return ResponseEntity.ok(optionalPerson.get());
        }
    }

    @PostMapping
    public ResponseEntity<Person> savePerson(@RequestBody Person person) {
        if (person.getAdres().getId() != null) {
            Optional<Adres> optionalAdres = adresRepository.findAdresById(person.getAdres().getId());
            if (optionalAdres.isPresent()) {
                person.setAdres(optionalAdres.get());
            }
        }

        Person savedPerson = personRepository.save(person);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(savedPerson.getId()).toUri();
        return ResponseEntity.created(location).body(savedPerson);
    }

    @PutMapping("/update/{personId}")
    public  ResponseEntity<Person> updatePerson(@PathVariable Long personId, @RequestBody Person person) {
        Optional<Person> optionalCategory = personRepository.findPersonById(personId);
        if (!optionalCategory.isPresent()){
            throw new ResourceNotFoundException("Person is not found");
        }else {
            person.setId(optionalCategory.get().getId());
            personRepository.save(person);
            return ResponseEntity.ok().body(person);
        }
    }

    @DeleteMapping("/delete/{personId}")
    public ResponseEntity<Person> deletePerson(@PathVariable Long personId) {
        Optional<Person> optionalCategory = personRepository.findPersonById(personId);
        if (!optionalCategory.isPresent()) {
            throw new ResourceNotFoundException("Person is not found");
        } else {
            personRepository.delete(optionalCategory.get());
            return ResponseEntity.noContent().build();
        }
    }


}
