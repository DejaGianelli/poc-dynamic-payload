package br.com.drivelab.dynamicpayload.api;

import br.com.drivelab.dynamicpayload.entities.LegalPerson;
import br.com.drivelab.dynamicpayload.entities.Person;

public class CreateLegalPersonRequest extends CreatePersonRequest {
    private String brandName;
    private String cnpj;
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
