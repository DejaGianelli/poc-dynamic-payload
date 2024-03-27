package br.com.drivelab.dynamicpayload.repositories;

import br.com.drivelab.dynamicpayload.entities.Person;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class PersonInMemoryRepository implements PersonRepository {
    private static final List<Person> PEOPLE = new ArrayList<>();

    @Override
    public void create(Person person) {
        PEOPLE.add(person);
    }

    @Override
    public Optional<Person> findByDocument(String document) {
        return PEOPLE.stream().filter(person -> person.getDocument().equals(document)).findAny();
    }
}
