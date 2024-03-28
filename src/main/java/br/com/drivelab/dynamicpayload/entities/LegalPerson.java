package br.com.drivelab.dynamicpayload.entities;

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

    public String getBrandName() {
        return brandName;
    }

    public String getCnpj() {
        return cnpj;
    }

    public String getCompanyName() {
        return companyName;
    }

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
