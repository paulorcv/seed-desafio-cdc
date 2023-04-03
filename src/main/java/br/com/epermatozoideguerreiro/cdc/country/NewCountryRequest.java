package br.com.epermatozoideguerreiro.cdc.country;

import javax.validation.constraints.NotBlank;

import br.com.epermatozoideguerreiro.cdc.shared.UniqueValue;

public class NewCountryRequest {

    @NotBlank
    @UniqueValue(domainClass = Country.class, fieldname = "name")
    private String name;
    
    public NewCountryRequest() {
    }

    public NewCountryRequest(@NotBlank String name) {
        this.name = name;
    }

    public Country toModel() {
        return new Country(name);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
