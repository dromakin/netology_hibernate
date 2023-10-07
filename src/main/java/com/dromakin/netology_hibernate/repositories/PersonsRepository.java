package com.dromakin.netology_hibernate.repositories;


import com.dromakin.netology_hibernate.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PersonsRepository extends JpaRepository<Person, Integer> {

    // создайте метод, который будет принимать название города (city) и возвращать Entity из базы данных, которые соответствуют этому city;
    List<Person> getAllByCityIgnoreCase(String city);

    // создайте метод, который будет принимать возраст (age) и возвращать Entity из базы данных, которые меньше переданного age и отсортированы по возрастанию;
    List<Person> getAllByAgeLessThan(int age);

    // создайте метод, который будет принимать имя и фамилию (name и surname) и возвращать Entity из базы данных, которые соответствуют сочетанию name и surname и являются Optional.
    Optional<Person> getPersonByNameAndSurname(String name, String surname);

}
