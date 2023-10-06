package com.dromakin.netology_hibernate.repositories;

import com.dromakin.netology_hibernate.exceptions.DataSaveException;
import com.dromakin.netology_hibernate.model.Person;
import com.dromakin.netology_hibernate.utils.ResourceReader;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PersonsRepositoryImpl implements PersonsRepository {

    @PersistenceContext
    private final EntityManager entityManager;

    private final ResourceReader resourceReader;

    public PersonsRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
        this.resourceReader = new ResourceReader();
    }

    @Override
    @Transactional
    public Person save(Person person) {

        try {
            entityManager.persist(person);
        } catch (Exception e) {
            throw new DataSaveException("Can't save data in DB!");
        }

        return person;
    }

    @Override
    @Transactional
    public void delete(Person person) {
        entityManager.remove(person);
    }

    @Override
    @Transactional
    public void update(Person person) {
        entityManager.refresh(person);
    }

    @Override
    public List<Person> findPersonsByCity(String city) {
        String queryString = String.format("From Person where city = '%s'", city);
        var query = entityManager.createQuery(queryString);
        return query.getResultList();
    }

    @Override
    public List<Person> findAll() {
        var query = entityManager.createQuery("From Person");
        return query.getResultList();
    }
}
