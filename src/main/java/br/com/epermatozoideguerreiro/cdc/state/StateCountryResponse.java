package br.com.epermatozoideguerreiro.cdc.state;

import br.com.epermatozoideguerreiro.cdc.country.Country;

public class StateCountryResponse {

    private Long id;
    private String name;

    public StateCountryResponse(Country country) {
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
