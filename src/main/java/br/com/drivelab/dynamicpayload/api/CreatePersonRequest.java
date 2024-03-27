package br.com.drivelab.dynamicpayload.api;

import br.com.drivelab.dynamicpayload.entities.Person;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public abstract Person.Builder<? extends Person.Builder<?>> toBuilder();
}
