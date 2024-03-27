package br.com.drivelab.dynamicpayload.api;

import br.com.drivelab.dynamicpayload.entities.Person;
import br.com.drivelab.dynamicpayload.repositories.PersonRepository;
import br.com.drivelab.dynamicpayload.services.CreatePersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/people")
public class PersonController {
    private final CreatePersonService createPersonService;
    private final PersonRepository repository;

    @Autowired
    public PersonController(CreatePersonService createPersonService, PersonRepository repository) {
        this.createPersonService = createPersonService;
        this.repository = repository;
    }

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody CreatePersonRequest request) {
        Person person = createPersonService.execute(request.toBuilder());
        repository.create(person);
        return ResponseEntity.ok().build();
    }
}
