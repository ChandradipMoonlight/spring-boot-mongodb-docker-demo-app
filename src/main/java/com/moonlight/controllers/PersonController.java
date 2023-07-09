package com.moonlight.controllers;

import com.moonlight.collections.Person;
import com.moonlight.dto.PersonRequest;
import com.moonlight.dto.PersonResponse;
import com.moonlight.service.IPersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(name = "/person")
public class PersonController {
    @Autowired
    private IPersonService personService;

    @PostMapping(name = "/save")
    public ResponseEntity<PersonResponse> save(@RequestBody Person person) {
        return Optional.of(personService.save(person))
                .map(response -> new ResponseEntity(response, HttpStatus.OK))
                .orElseThrow(()-> new RuntimeException("Error in Persist of data"));
    }

    @DeleteMapping(value = "/delete/{personId}")
    public ResponseEntity<String> deletePerson(@PathVariable Integer personId) {
        return new ResponseEntity<>(personService.deletePerson(personId), HttpStatus.OK);
    }

    @PutMapping(value = "/update")
    public ResponseEntity<PersonResponse> updatePerson(@RequestParam Integer personsId, @RequestBody PersonRequest personRequest) {
        return new ResponseEntity<>(personService.updatePersonDetails(personsId, personRequest), HttpStatus.OK);
    }

    @GetMapping(value = "all")
    public ResponseEntity<PersonResponse> getAllPersons() {
        PersonResponse allPersons = personService.getAllPersons();
        return new ResponseEntity<>(allPersons, HttpStatus.OK);
    }
}
