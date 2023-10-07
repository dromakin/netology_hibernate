package com.dromakin.netology_hibernate.controllers;

import com.dromakin.netology_hibernate.config.SwaggerConfig;
import com.dromakin.netology_hibernate.dto.PersonDTO;
import com.dromakin.netology_hibernate.model.Person;
import com.dromakin.netology_hibernate.services.PersonService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/persons/")
@AllArgsConstructor
public class PersonsController {

    private final PersonService service;

    @Operation(
            summary = "Get list of persons by city",
            security = {@SecurityRequirement(name = SwaggerConfig.BASIC_AUTH_SECURITY_SCHEME)},
            responses = {
                    @ApiResponse(description = "Persons",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = List.class))),
                    @ApiResponse(responseCode = "400", description = "Persons not found")
            }
    )
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

    @Operation(
            summary = "Get list of persons with age less then",
            security = {@SecurityRequirement(name = SwaggerConfig.BASIC_AUTH_SECURITY_SCHEME)},
            responses = {
                    @ApiResponse(description = "Persons",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = List.class))),
                    @ApiResponse(responseCode = "400", description = "Persons not found")
            }
    )
    @GetMapping("age-less-then")
    @ResponseBody
    public List<PersonDTO> getByAgeLessThen(@RequestParam int age) {
        List<Person> persons = service.getPersonsByAgeLessThan(age);
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

    @Operation(
            summary = "Get person by name and surname",
            security = {@SecurityRequirement(name = SwaggerConfig.BASIC_AUTH_SECURITY_SCHEME)},
            responses = {
                    @ApiResponse(description = "The person",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = PersonDTO.class))),
                    @ApiResponse(responseCode = "400", description = "Person not found")
            }
    )
    @GetMapping("by-name-surname")
    @ResponseBody
    public PersonDTO getByNameAndSurname(
            @RequestParam String name,
            @RequestParam String surname
    ) {
        Person person = service.getPersonByNameAndSurname(name, surname);
        return PersonDTO.builder()
                .id(person.getId())
                .name(person.getName())
                .surname(person.getSurname())
                .age(person.getAge())
                .phoneNumber(person.getPhoneNumber())
                .city(person.getCity())
                .build();
    }
}
