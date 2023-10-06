package com.dromakin.netology_hibernate.controllers;

import com.dromakin.netology_hibernate.dto.PersonDTO;
import com.dromakin.netology_hibernate.model.Person;
import com.dromakin.netology_hibernate.services.PersonService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/persons/")
@AllArgsConstructor
public class PersonsController {

    private final PersonService service;
    @GetMapping("by-city")
    @ResponseBody
    public List<PersonDTO> getByCity(@RequestParam String city) {
        List<Person> persons = service.getPersonsByCity(city);
        return persons.stream()
                .map(
                        x -> PersonDTO.builder()
                                .id(x.getId())
                                .name(x.getName())
                                .surname(x.getSurname())
                                .age(x.getAge())
                                .phoneNumber(x.getPhoneNumber())
                                .city(x.getCity())
                                .build())
                .collect(Collectors.toList());
    }

}
