package br.com.epermatozoideguerreiro.cdc.purchase;

import br.com.epermatozoideguerreiro.cdc.country.Country;

public class PurchaseCountryResponse {

    private Long id;
    private String name;

    public PurchaseCountryResponse(Country country) {
        this.id = country.getId();
        this.name = country.getName();
    }
    public Long getId() {
        return id;
    }
    public String getName() {
        return name;
    }

    
}
