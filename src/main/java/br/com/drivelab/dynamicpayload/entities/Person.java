package br.com.drivelab.dynamicpayload.entities;

import java.util.UUID;

public abstract class Person {
    private final UUID id;
    protected PersonType type;

    protected Person(Builder<?> builder) {
        this.id = builder.id;
    }

    public abstract String getDocument();

    public UUID getId() {
        return id;
    }

    public PersonType getType() {
        return type;
    }

    public static abstract class Builder<T extends Builder<T>> {
        private UUID id = UUID.randomUUID();

        public T setId(UUID id) {
            this.id = id;
            return self();
        }

        public abstract Person build();

        public abstract T self();
    }
}
