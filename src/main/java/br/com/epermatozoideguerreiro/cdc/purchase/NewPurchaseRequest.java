package br.com.epermatozoideguerreiro.cdc.purchase;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.util.Assert;

import br.com.epermatozoideguerreiro.cdc.book.BookRepository;
import br.com.epermatozoideguerreiro.cdc.country.Country;
import br.com.epermatozoideguerreiro.cdc.country.CountryRepository;
import br.com.epermatozoideguerreiro.cdc.shared.Documento;
import br.com.epermatozoideguerreiro.cdc.state.State;
import br.com.epermatozoideguerreiro.cdc.state.StateRepository;

public class NewPurchaseRequest {

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
    private Long idCountry;

    private Long idState;

    @NotBlank
    private String phoneNumber;

    @NotBlank
    private String cep;

    @NotNull
    private BigDecimal total;

    @NotEmpty
    private List<@Valid ItemOrderRequest> itemsOrder;

    public NewPurchaseRequest() {
    }

    public NewPurchaseRequest(@NotBlank @Email String email, @NotBlank String name, @NotBlank String lastName,
            @NotBlank @Documento String document, @NotBlank String address, @NotBlank String complement,
            @NotBlank String city, @NotNull Long idCountry, Long idState, @NotBlank String phoneNumber,
            @NotBlank String cep, @NotNull BigDecimal total, @NotEmpty List<@Valid ItemOrderRequest> itemsOrder) {
        this.email = email;
        this.name = name;
        this.lastName = lastName;
        this.document = document;
        this.address = address;
        this.complement = complement;
        this.city = city;
        this.idCountry = idCountry;
        this.idState = idState;
        this.phoneNumber = phoneNumber;
        this.cep = cep;
        this.total = total;
        this.itemsOrder = itemsOrder;
    }

    public Purchase toModel(CountryRepository countryRepository, StateRepository stateRepository,
            BookRepository bookRepository) {

        @NotNull
        Optional<Country> countryOptional = countryRepository.findById(idCountry);
        Assert.state(countryOptional.isPresent(), "Não existe o país com o id: " + idCountry + " no banco");

        Country country = countryOptional.get();
        State state = null;

        if (country.hasStates()) {
            Optional<State> stateOptional = stateRepository.findById(idState);
            Assert.state(stateOptional.isPresent(), "Não existe estado  com o id: " + idState + " no banco");
            state = stateOptional.get();
        }

        List<ItemOrder> itemsOrderModel = null;

        if (!itemsOrder.isEmpty()) {
            itemsOrderModel = itemsOrder.stream().map(item -> item.toModel(bookRepository))
                    .collect(Collectors.toList());
        }

        return new Purchase(
                email,
                name,
                lastName,
                document,
                address,
                complement,
                city,
                country,
                state,
                phoneNumber,
                cep,
                total,
                itemsOrderModel);

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

    public Long getIdCountry() {
        return idCountry;
    }

    public void setIdCountry(Long idCountry) {
        this.idCountry = idCountry;
    }

    public Long getIdState() {
        return idState;
    }

    public void setIdState(Long idState) {
        this.idState = idState;
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

    public List<ItemOrderRequest> getItemsOrder() {
        return itemsOrder;
    }

    public void setItemsOrder(List<ItemOrderRequest> itemsOrder) {
        this.itemsOrder = itemsOrder;
    }

}
