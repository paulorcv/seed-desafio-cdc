package br.com.epermatozoideguerreiro.cdc.country;

import javax.validation.constraints.NotBlank;

public class NewCountryRequest {

    @NotBlank
    private String name;


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
