package com.dromakin.netology_hibernate.repositories;


import com.dromakin.netology_hibernate.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface PersonsRepository extends JpaRepository<Person, Integer> {

    // создайте метод, который будет принимать название города (city) и возвращать Entity из базы данных, которые соответствуют этому city;
    @Query("SELECT p FROM Person p where lower(p.city) = :city")
    List<Person> getAllPersonsByCity(String city);

    // создайте метод, который будет принимать возраст (age) и возвращать Entity из базы данных, которые меньше переданного age и отсортированы по возрастанию;
    @Query("SELECT p FROM Person p where p.age < :age")
    List<Person> getAllPersonsByAgeLessThan(int age);

    // создайте метод, который будет принимать имя и фамилию (name и surname) и возвращать Entity из базы данных, которые соответствуют сочетанию name и surname и являются Optional.
    @Query("SELECT p FROM Person p where p.name = :name and p.surname = :surname")
    Optional<Person> getPersonByFullName(String name, String surname);

}
