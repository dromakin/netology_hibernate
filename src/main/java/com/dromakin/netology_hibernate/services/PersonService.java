/*
 * File:     PersonService
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

import java.util.List;
import java.util.Optional;

public interface PersonService {

    List<Person> getPersonsByCity(String city);

    List<Person> getPersonsByAgeLessThan(int age);

    Person getPersonByNameAndSurname(String name, String surname);

}
