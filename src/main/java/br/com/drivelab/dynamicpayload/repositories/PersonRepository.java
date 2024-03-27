package br.com.drivelab.dynamicpayload.repositories;

import br.com.drivelab.dynamicpayload.entities.Person;

import java.util.Optional;

public interface PersonRepository {
    void create(Person person);

    Optional<Person> findByDocument(String document);
}
