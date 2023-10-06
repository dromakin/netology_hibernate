package com.dromakin.netology_hibernate.repositories;


import com.dromakin.netology_hibernate.model.Person;

import java.util.List;

public interface PersonsRepository {
    Person save(Person person);

    void delete(Person person);

    void update(Person person);

    List<Person> findPersonsByCity(String city);

    List<Person> findAll();

}
