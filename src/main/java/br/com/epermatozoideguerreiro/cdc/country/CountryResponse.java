package br.com.epermatozoideguerreiro.cdc.country;

public class CountryResponse {

    private String name;
    private Long id;

    public CountryResponse(Country country) {
        this.name = country.getName();
        this.id = country.getId();
    }

    public String getName() {
        return name;
    }

    public Long getId() {
        return id;
    }

    
    
}
