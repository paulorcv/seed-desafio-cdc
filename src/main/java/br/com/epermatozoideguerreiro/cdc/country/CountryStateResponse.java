package br.com.epermatozoideguerreiro.cdc.country;

import br.com.epermatozoideguerreiro.cdc.state.State;

public class CountryStateResponse {

    private Long id;
    private String name;

    public CountryStateResponse(State state) {
        this.name = state.getName();
        this.id = state.getId();
    }

    public String getName() {
        return name;
    }

    public Long getId() {
        return id;
    }
    
}
