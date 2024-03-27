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
                .setBrandName("Brand")
                .setCnpj("1234")
                .setCompanyName("Company Ltda")
                .build();

        assertEquals(guid, legalPerson.getId());
        assertEquals("Brand", legalPerson.getBrandName());
        assertEquals("1234", legalPerson.getCnpj());
        assertEquals("Company Ltda", legalPerson.getCompanyName());
    }

    @Test
    void natural() {
        var guid = UUID.randomUUID();
        NaturalPerson naturalPerson = new NaturalPerson.Builder()
                .setId(guid)
                .setName("Brand")
                .setCpf("1234")
                .build();

        assertEquals(guid, naturalPerson.getId());
        assertEquals("Brand", naturalPerson.getName());
        assertEquals("1234", naturalPerson.getCpf());
    }
}
