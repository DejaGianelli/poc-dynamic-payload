package br.com.drivelab.dynamicpayload.api;

import br.com.drivelab.dynamicpayload.entities.LegalPerson;
import br.com.drivelab.dynamicpayload.entities.Person;
import jakarta.validation.constraints.NotEmpty;
import org.hibernate.validator.constraints.br.CNPJ;

public class CreateLegalPersonRequest extends CreatePersonRequest {
    @NotEmpty
    private String brandName;
    @NotEmpty
    @CNPJ
    private String cnpj;
    @NotEmpty
    private String companyName;

    public CreateLegalPersonRequest() {
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    @Override
    public Person.Builder<LegalPerson.Builder> toBuilder() {
        return new LegalPerson.Builder()
                .setBrandName(brandName)
                .setCompanyName(companyName)
                .setCnpj(cnpj);
    }
}
