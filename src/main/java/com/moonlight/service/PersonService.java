package com.moonlight.service;

import com.moonlight.collections.Address;
import com.moonlight.collections.Person;
import com.moonlight.dto.PersonRequest;
import com.moonlight.dto.PersonResponse;
import com.moonlight.repository.PersonRepo;
import com.moonlight.constants.AppConstants;
import com.moonlight.utils.AppUtils;
import com.moonlight.utils.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class PersonService implements IPersonService{

    @Autowired
    private PersonRepo personRepo;
    @Override
    public PersonResponse save(Person person) {
        log.info("Inside Class[PersonService] Method[Save] request : {}", JsonUtils.javaToJson(person));
        if (person.getPersonId() == null) {
            Integer id = getUniquePersonId();
            person.setPersonId(id);
        }
        Person savedPerson = personRepo.save(person);
        return processResponse(savedPerson);
    }

    @Override
    public String deletePerson(Integer personId) {
        log.info("Inside Class[PersonService] Method[deletePerson] personId : {}", personId);
        Optional<Person> person = personRepo.findById(personId);
        if (person.isPresent()) {
            personRepo.delete(person.get());
            return "Person Data Deleted Successfully!";
        } else {
            return "Person is not found with Id"+personId;
        }
    }

    @Override
    public PersonResponse updatePersonDetails(Integer personId, PersonRequest personRequest) {
        log.info("Inside class[PersonService] Method[updatePersonDetails] personId : {}\nPersonRequest : ", personId, JsonUtils.javaToJson(personRequest));
        String message = "Person Details updated Successfully with personId : "+personId;
        if (!personRepo.findById(personId).isPresent()) {
            message = "Person Details not found with Id" + personId;
            return processResponse(null, message);
        }
        Person person = mapPersonRequestToPerson(personRequest);
        person = personRepo.save(person);
        return processResponse(person, message);
    }

    @Override
    public PersonResponse getAllPersons() {
        List<Person> allPersons = personRepo.findAll();
        String message = "Sorry details not found!";
        if (allPersons != null && !allPersons.isEmpty()) {
            PersonResponse personResponse = new PersonResponse();
            message = "All persons are fetched successfully!";
            personResponse.setPerson(allPersons);
            AppUtils.getPersonSuccessResult(personResponse, message);
            log.info("inside Method[getAllPersons] All Persons: ", JsonUtils.javaToJson(personResponse));
            return personResponse;
        }
        return processResponse(null, message);
    }

    private Person mapPersonRequestToPerson(PersonRequest personRequest) {
        Person person = new Person();
        if (personRequest.getFirstName()!=null) {
            person.setFirstName(personRequest.getFirstName());
        }
        if (personRequest.getLastName() != null) {
            person.setLastName(personRequest.getLastName());
        }
        if (personRequest.getAge() != null) {
            person.setAge(personRequest.getAge());
        }
        if (personRequest.getHobbies() != null) {
            person.setHobbies(personRequest.getHobbies());
        }
        if (personRequest.getAddresses()!=null && !personRequest.getAddresses().isEmpty()) {
            person.setAddresses(getAddressList(personRequest.getAddresses()));
        }
        return person;
    }
    private List<Address> getAddressList(List<Address> addresses) {
        List<Address> addressList = new ArrayList<>();
        addresses.forEach(address -> {
            Address address1 = new Address();
            if (address.getAddress1() != null) {
                address1.setAddress1(address.getAddress1());
            }
            if (address.getCity() != null) {
                address1.setCity(address.getCity());
            }
            if (address.getState() != null) {
                address1.setState(address.getState());
            }
            if (address.getPinCode() != null) {
                address1.setPinCode(address.getPinCode());
            }
            addressList.add(address1);
        } );
        return addressList;
    }

    private PersonResponse processResponse(Person savedPerson) {
        PersonResponse personResponse = new PersonResponse();
        if (savedPerson != null) {
            personResponse.setPerson(savedPerson);
            personResponse.setStatus(AppConstants.SUCCESS);
            personResponse.setStatusCode(AppConstants.SUCCESS_CODE);
        } else {
            personResponse.setStatusCode(AppConstants.FAILURE_CODE);
            personResponse.setStatus(AppConstants.FAIL);
        }
        return personResponse;
    }
    private PersonResponse processResponse(Person savedPerson, String message) {
        PersonResponse personResponse = new PersonResponse();
        if (savedPerson != null) {
            AppUtils.getPersonSuccessResult(personResponse, message);
            personResponse.setPerson(savedPerson);
        } else {
           AppUtils.getPersonFailResult(personResponse, message);
        }
        log.info("Method[processResponse] PersonResponse : {}", JsonUtils.javaToJson(personResponse));
        return personResponse;
    }



    public Integer getUniquePersonId() {
        Integer id = personRepo.findAll()
                .stream()
                .map(person -> person.getPersonId())
                .max((id1, id2) -> id1.compareTo(id2))
                .orElse(0);
        return id = id +1;
    }
}
