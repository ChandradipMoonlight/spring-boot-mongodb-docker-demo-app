package com.moonlight.service;

import com.moonlight.collections.Person;
import com.moonlight.dto.PersonRequest;
import com.moonlight.dto.PersonResponse;

import java.util.List;

public interface IPersonService {
    PersonResponse save(Person person);

    String deletePerson(Integer personId);

    PersonResponse updatePersonDetails(Integer personsId, PersonRequest person);

    PersonResponse getAllPersons();
}
