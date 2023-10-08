package com.dromakin.netology_hibernate.controllers;

import com.dromakin.netology_hibernate.config.SwaggerConfig;
import com.dromakin.netology_hibernate.dto.MsgResponseDTO;
import com.dromakin.netology_hibernate.dto.PersonDTO;
import com.dromakin.netology_hibernate.model.Person;
import com.dromakin.netology_hibernate.services.PersonService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/persons/")
@AllArgsConstructor
public class PersonsController {

    private final PersonService service;

    @Operation(
            summary = "Check authenticated user",
            security = {@SecurityRequirement(name = SwaggerConfig.AUTH_SECURITY_SCHEME)},
            responses = {
                    @ApiResponse(description = "Message",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = MsgResponseDTO.class))),
                    @ApiResponse(responseCode = "400", description = "User not authenticated")
            }
    )
    @GetMapping("/check-username")
    public MsgResponseDTO checkUsername(@RequestParam("username") String username, Authentication authentication) {
        if (authentication.getName().equals(username)) {
            return MsgResponseDTO.builder().message("Username matches authenticated user.").build();
        } else {
            return MsgResponseDTO.builder().message("Username does not match authenticated user.").build();
        }
    }

    @Operation(
            summary = "Get list of persons by city",
            security = {@SecurityRequirement(name = SwaggerConfig.AUTH_SECURITY_SCHEME)},
            responses = {
                    @ApiResponse(description = "Persons",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = List.class))),
                    @ApiResponse(responseCode = "400", description = "Persons not found")
            }
    )
    @GetMapping("by-city")
    @ResponseBody
    @PreAuthorize("hasAuthority('READ')")
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
            security = {@SecurityRequirement(name = SwaggerConfig.AUTH_SECURITY_SCHEME)},
            responses = {
                    @ApiResponse(description = "Persons",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = List.class))),
                    @ApiResponse(responseCode = "400", description = "Persons not found")
            }
    )
    @GetMapping("age-less-then")
    @ResponseBody
    @PreAuthorize("hasAuthority('READ') and hasAuthority('WRITE')")
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
            security = {@SecurityRequirement(name = SwaggerConfig.AUTH_SECURITY_SCHEME)},
            responses = {
                    @ApiResponse(description = "The person",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = PersonDTO.class))),
                    @ApiResponse(responseCode = "400", description = "Person not found")
            }
    )
    @GetMapping("by-name-surname")
    @ResponseBody
    @PreAuthorize("hasAuthority('UPDATE') and hasAuthority('DELETE')")
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
