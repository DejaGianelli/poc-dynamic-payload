# POC Dynamic Payload

It is a very common requirement of an API to have an endpoint in which the payload is dynamic.
That is because sometimes the resource has different types that require different information and validations

For example, I might have two different types of person in my software: legal and natural. There is some information 
that only makes sense to natural person and vice-versa, as below:

*PS: Of course this a simple example, just to get the ideia* 

This is a json representation of legal person:
```json
{
    "type": "legal",
    "brandName": "Comp",
    "cnpj": "87530523000128",
    "companyName": "Company LTDA"
}
```

And this is a json representation of a natural person:
```json
{
  "type": "natural",
  "firstName": "Fulano",
  "lastName": "Silva",
  "cpf": "00126014035"
}
```

Since in this example a Legal Person and a Natural Person ARE Persons (IS A relationship) 
and these to representations have specific properties and also share some common ones, the
approach is to use Inheritance in the Request Object. In order to Spring to know which implementation
to instantiate when payload is received by the controller the following Jackson Configurations are necessary:

```java
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        property = "type",
        visible = true
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = CreateNaturalPersonRequest.class, name = "natural"),
        @JsonSubTypes.Type(value = CreateLegalPersonRequest.class, name = "legal")
})
public abstract class CreatePersonRequest {
    private String type;

    // Getters and setters omitted for brevity...
    
    public abstract Person.Builder<? extends Person.Builder<?>> toBuilder();
}
```

Essentially, based on the *type* property, Jackson will know which implementation to choose.

This way, you don't pollute a single request class with a bunch of properties of all different implementations
and also, avoid having validators with complex conditions to handle each case, hence ensuring the separation
of concerns.

You may have noticed the abstract *toBuilder()* method. That's because I don't want to pass the request
object directly to the service because it belongs to a different layer (infrastructure). 
Instead, made the service to receive an abstract builder. 

I could have passed an DTO or even an abstract Person instead, but for educational purposes I choose to
use Builder Pattern that scales very when you have to create objects that are complex or have a lot of properties.
In that way, you don't have to make constructors with lots os properties that are hard do read, maintain and very
error-prone. Yes, I know my objects are not complex, but you got the idea.

This Builder Pattern in particular is very interesting because it can handle Inheritance with chaining methods.
To do that, you must do some clever use of Java's Generics. This Pattern is explained in Chapter 2 of 
[Effective Java Third Edition](https://a.co/d/9TF63Tf).

```java
public abstract class Person {
    private final UUID id;
    protected PersonType type;

    protected Person(Builder<?> builder) {
        this.id = builder.id;
    }

    public abstract String document();

    // Getters and setter omitted for brevity
    
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
```

```java
public class LegalPerson extends Person {
    private final String brandName;
    private final String cnpj;
    private final String companyName;

    public LegalPerson(Builder builder) {
        super(builder);
        super.type = PersonType.LEGAL;
        this.brandName = builder.brandName;
        this.cnpj = builder.cnpj;
        this.companyName = builder.companyName;
    }

    // Getters and Setters omitted for brevity
    
    @Override
    public String document() {
        return cnpj;
    }

    public static class Builder extends Person.Builder<Builder> {
        private String brandName;
        private String cnpj;
        private String companyName;

        public Builder setBrandName(String brandName) {
            this.brandName = brandName;
            return this;
        }

        public Builder setCnpj(String cnpj) {
            this.cnpj = cnpj;
            return this;
        }

        public Builder setCompanyName(String companyName) {
            this.companyName = companyName;
            return this;
        }

        @Override
        public LegalPerson build() {
            return new LegalPerson(this);
        }

        @Override
        public Builder self() {
            return this;
        }
    }
}
```

```java
public class NaturalPerson extends Person {
    private final String firstName;
    private final String lastName;
    private final String cpf;

    public NaturalPerson(Builder builder) {
        super(builder);
        super.type = PersonType.NATURAL;
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.cpf = builder.cpf;
    }

    // Getters and Setters omitted for brevity
    
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
```