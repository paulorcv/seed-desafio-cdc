package br.com.epermatozoideguerreiro.cdc.state;

public class StateResponse {

    private Long id;
    private String name;
    private StateCountryResponse country;

    public StateResponse(State state) {
        this.name = state.getName();
        this.id = state.getId();
        this.country = new StateCountryResponse(state.getCountry());
    }

    public String getName() {
        return name;
    }

    public Long getId() {
        return id;
    }

    public StateCountryResponse getCountry() {
        return country;
    }
   
    
}
