package br.com.epermatozoideguerreiro.cdc.state;

import java.util.Optional;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.util.Assert;

import br.com.epermatozoideguerreiro.cdc.country.Country;
import br.com.epermatozoideguerreiro.cdc.country.CountryRepository;

public class NewStateRequest {

    @NotBlank
    private String name;

    @NotNull
    private Long idCountry;

    public NewStateRequest(@NotBlank String name, @NotNull Long idCountry) {
        this.name = name;
        this.idCountry = idCountry;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getIdCountry() {
        return idCountry;
    }

    public void setIdCountry(Long idCountry) {
        this.idCountry = idCountry;
    }

    public State toModel(CountryRepository countryRepository) {

        @NotNull
        Optional<Country> country = countryRepository.findById(idCountry);

        Assert.state(country.isPresent(), "Não existe país com o id: " + idCountry + " no banco");
        
        return new State(name, country.get());

    }

}
