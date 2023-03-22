package br.com.epermatozoideguerreiro.cdc.state;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import br.com.epermatozoideguerreiro.cdc.country.Country;

@Entity
public class State {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;    

    @NotBlank
    private String name;

    @NotNull
    @ManyToOne
    private Country country;

    @Deprecated
    public State() {
    }

    public State(@NotBlank String name, Country country) {
        this.name = name;
        this.country = country;
    }
    
    @Override
    public String toString() {
        return "State [id=" + id + ", name=" + name + "]";
    }

    public String getName() {
        return name;
    }

    public Long getId() {
        return id;
    }

    public Country getCountry() {
        return country;
    }    

    
    

}
