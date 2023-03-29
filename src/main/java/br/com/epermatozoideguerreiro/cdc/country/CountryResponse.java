package br.com.epermatozoideguerreiro.cdc.country;

import java.util.List;
import java.util.stream.Collectors;

public class CountryResponse {

    private String name;
    private Long id;
    private List<CountryStateResponse> states;

    public CountryResponse(Country country) {
        this.name = country.getName();
        this.id = country.getId();
        if(country.getStates()!=null && country.getStates().size()>0) this.states = country.getStates().stream().map(s -> new CountryStateResponse(s)).collect(Collectors.toList());
    }

    public String getName() {
        return name;
    }

    public Long getId() {
        return id;
    }

    public List<CountryStateResponse> getStates() {
        return states;
    }



}
