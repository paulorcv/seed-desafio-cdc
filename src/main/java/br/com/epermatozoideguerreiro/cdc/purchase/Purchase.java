package br.com.epermatozoideguerreiro.cdc.purchase;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import br.com.epermatozoideguerreiro.cdc.country.Country;
import br.com.epermatozoideguerreiro.cdc.shared.Documento;
import br.com.epermatozoideguerreiro.cdc.state.State;

public class Purchase {

    @NotBlank
    @Email
    private String email;

    @NotBlank
    private String name;

    @NotBlank
    private String lastName;

    @NotBlank
    @Documento
    private String document;

    @NotBlank
    private String address;

    @NotBlank
    private String complement;

    @NotBlank
    private String city;

    @NotNull
    private Country country;

    private State state;

    @NotBlank
    private String phoneNumber;

    @NotBlank
    private String cep;

    public Purchase() {
    }

    public Purchase(@NotBlank @Email String email, @NotBlank String name, @NotBlank String lastName,
            @NotBlank String document, @NotBlank String address, @NotBlank String complement, @NotBlank String city,
            @NotNull Country country, State state, @NotBlank String phoneNumber, @NotBlank String cep) {
        this.email = email;
        this.name = name;
        this.lastName = lastName;
        this.document = document;
        this.address = address;
        this.complement = complement;
        this.city = city;
        this.country = country;
        this.state = state;
        this.phoneNumber = phoneNumber;
        this.cep = cep;
    }

    @Override
    public String toString() {
        return "Purchase [email=" + email + ", name=" + name + ", lastName=" + lastName + ", document=" + document
                + ", address=" + address + ", complement=" + complement + ", city=" + city + ", country=" + country
                + ", state=" + state + ", phoneNumber=" + phoneNumber + ", cep=" + cep + "]";
    }

    

}
