package br.com.drivelab.dynamicpayload;

import br.com.drivelab.dynamicpayload.entities.LegalPerson;
import br.com.drivelab.dynamicpayload.entities.NaturalPerson;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PersonTest {
    @Test
    void legal() {
        var guid = UUID.randomUUID();
        LegalPerson legalPerson = new LegalPerson.Builder()
                .setId(guid)
                .setBrandName("Comp")
                .setCnpj("1234")
                .setCompanyName("Company Ltda")
                .build();

        assertEquals(guid, legalPerson.getId());
        assertEquals("Comp", legalPerson.getBrandName());
        assertEquals("1234", legalPerson.getCnpj());
        assertEquals("Company Ltda", legalPerson.getCompanyName());
    }

    @Test
    void natural() {
        var guid = UUID.randomUUID();
        NaturalPerson naturalPerson = new NaturalPerson.Builder()
                .setId(guid)
                .setFirstName("Fulano")
                .setLastName("Silva")
                .setCpf("20258992034")
                .build();

        assertEquals(guid, naturalPerson.getId());
        assertEquals("Fulano", naturalPerson.getFirstName());
        assertEquals("Silva", naturalPerson.getLastName());
        assertEquals("20258992034", naturalPerson.getCpf());
    }
}
