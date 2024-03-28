package br.com.drivelab.dynamicpayload.api;

import br.com.drivelab.dynamicpayload.entities.NaturalPerson;
import br.com.drivelab.dynamicpayload.entities.Person;

public class CreateNaturalPersonRequest extends CreatePersonRequest {
    private String firstName;
    private String lastName;
    private String cpf;

    public CreateNaturalPersonRequest() {
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    @Override
    public Person.Builder<NaturalPerson.Builder> toBuilder() {
        return new NaturalPerson.Builder()
                .setFirstName(firstName)
                .setLastName(lastName)
                .setCpf(cpf);
    }
}
