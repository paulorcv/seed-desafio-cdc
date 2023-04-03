package br.com.epermatozoideguerreiro.cdc.purchase;

import br.com.epermatozoideguerreiro.cdc.state.State;

public class PurchaseStateResponse {

    private Long id;
    private String name;
    private PurchaseCountryResponse country;

    public PurchaseStateResponse(State state) {
        this.name = state.getName();
        this.id = state.getId();
        this.country = new PurchaseCountryResponse(state.getCountry());
    }

    public String getName() {
        return name;
    }

    public Long getId() {
        return id;
    }

    public PurchaseCountryResponse getCountry() {
        return country;
    }
   
    
}
