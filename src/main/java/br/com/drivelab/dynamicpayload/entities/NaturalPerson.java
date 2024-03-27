package br.com.drivelab.dynamicpayload.entities;

public class NaturalPerson extends Person {
    private final String name;
    private final String cpf;

    public NaturalPerson(Builder builder) {
        super(builder);
        super.type = PersonType.NATURAL;
        this.name = builder.name;
        this.cpf = builder.cpf;
    }

    public String getName() {
        return name;
    }

    public String getCpf() {
        return cpf;
    }

    @Override
    public String getDocument() {
       return cpf;
    }

    public static class Builder extends Person.Builder<Builder> {
        private String name;
        private String cpf;

        public Builder setName(String name) {
            this.name = name;
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
