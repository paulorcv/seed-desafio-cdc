package br.com.epermatozoideguerreiro.cdc.purchase;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import br.com.epermatozoideguerreiro.cdc.country.Country;
import br.com.epermatozoideguerreiro.cdc.shared.Documento;
import br.com.epermatozoideguerreiro.cdc.state.State;

@Entity
public class Purchase {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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
    @ManyToOne
    private Country country;

    @ManyToOne
    private State state;

    @NotBlank
    private String phoneNumber;

    @NotBlank
    private String cep;

    @NotNull
    private BigDecimal total;

    @NotEmpty
    @OneToMany(cascade = CascadeType.ALL)
    private List<@Valid ItemOrder> itemsOrder;    

    public Purchase() {
    }    

    public Purchase(@NotBlank @Email String email, @NotBlank String name, @NotBlank String lastName,
            @NotBlank @Documento String document, @NotBlank String address, @NotBlank String complement,
            @NotBlank String city, @NotNull Country country, State state, @NotBlank String phoneNumber,
            @NotBlank String cep, @NotNull BigDecimal total, @NotEmpty List<@Valid ItemOrder> itemsOrder) {
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
        this.total = total;
        this.itemsOrder = itemsOrder;
    }



    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getDocument() {
        return document;
    }

    public void setDocument(String document) {
        this.document = document;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getComplement() {
        return complement;
    }

    public void setComplement(String complement) {
        this.complement = complement;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public List<ItemOrder> getItemsOrder() {
        return itemsOrder;
    }

    public void setItemsOrder(List<ItemOrder> itemsOrder) {
        this.itemsOrder = itemsOrder;
    }

    @Override
    public String toString() {
        return "Purchase [id=" + id + ", email=" + email + ", name=" + name + ", lastName=" + lastName + ", document="
                + document + ", address=" + address + ", complement=" + complement + ", city=" + city + ", country="
                + country + ", state=" + state + ", phoneNumber=" + phoneNumber + ", cep=" + cep + ", total=" + total
                + ", itemsOrder=" + itemsOrder + "]";
    }


    

}
