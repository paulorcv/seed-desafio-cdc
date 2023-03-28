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
    
    
    public String getName() {
        return name;
    }

    public Long getId() {
        return id;
    }
    
    public Country getCountry() {
        return country;
    }    
    
    
    @Override
    public String toString() {
        return "State [id=" + id + ", name=" + name + "]";
    }

    public boolean belongsToCountry(Country country2) {
        return country.equals(country2);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        State other = (State) obj;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        return true;
    }
    
    

}
