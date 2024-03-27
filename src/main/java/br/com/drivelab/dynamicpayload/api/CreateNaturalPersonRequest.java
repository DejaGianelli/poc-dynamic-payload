package br.com.drivelab.dynamicpayload.api;

import br.com.drivelab.dynamicpayload.entities.NaturalPerson;
import br.com.drivelab.dynamicpayload.entities.Person;

public class CreateNaturalPersonRequest extends CreatePersonRequest {
    private String name;
    private String cpf;

    public CreateNaturalPersonRequest() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
                .setName(name)
                .setCpf(cpf);
    }
}
