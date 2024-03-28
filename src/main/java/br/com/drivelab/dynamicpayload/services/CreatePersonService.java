package br.com.drivelab.dynamicpayload.services;

import br.com.drivelab.dynamicpayload.entities.Person;
import br.com.drivelab.dynamicpayload.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CreatePersonService {
    private final PersonRepository repository;

    @Autowired
    public CreatePersonService(PersonRepository repository) {
        this.repository = repository;
    }

    public Person execute(Person.Builder<? extends Person.Builder<?>> builder) {
        Person person = builder.build();
        Optional<Person> personWithDocument = repository.findByDocument(person.document());
        if (personWithDocument.isPresent()) {
            throw new RuntimeException("There is already a person with the document " + person.document());
        }
        return person;
    }
}
