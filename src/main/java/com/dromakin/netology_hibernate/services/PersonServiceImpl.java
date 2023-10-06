/*
 * File:     PersonServiceImpl
 * Package:  com.dromakin.netology_hibernate.services
 * Project:  netology_hibernate
 *
 * Created by dromakin as 06.10.2023
 *
 * author - dromakin
 * maintainer - dromakin
 * version - 2023.10.06
 * copyright - ORGANIZATION_NAME Inc. 2023
 */
package com.dromakin.netology_hibernate.services;

import com.dromakin.netology_hibernate.model.Person;
import com.dromakin.netology_hibernate.repositories.PersonsRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PersonServiceImpl implements PersonService {

    private final PersonsRepository personsRepository;

    @Override
    public List<Person> getPersonsByCity(String city) {
        return personsRepository.findPersonsByCity(city);
    }
}
