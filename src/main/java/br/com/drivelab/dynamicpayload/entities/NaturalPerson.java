package br.com.drivelab.dynamicpayload.entities;

public class NaturalPerson extends Person {
    private final String firstName;
    private final String lastName;
    private final String cpf;

    protected NaturalPerson(Builder builder) {
        super(builder);
        super.type = PersonType.NATURAL;
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.cpf = builder.cpf;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getCpf() {
        return cpf;
    }

    @Override
    public String document() {
       return cpf;
    }

    public static class Builder extends Person.Builder<Builder> {
        private String firstName;
        private String lastName;
        private String cpf;

        public Builder setLastName(String lastName) {
            this.lastName = lastName;
            return self();
        }

        public Builder setFirstName(String firstName) {
            this.firstName = firstName;
            return self();
        }

        public Builder setCpf(String cpf) {
            this.cpf = cpf;
            return self();
        }

        @Override
        public NaturalPerson build() {
            return new NaturalPerson(this);
        }

        @Override
        public NaturalPerson.Builder self() {
            return this;
        }
    }
}
