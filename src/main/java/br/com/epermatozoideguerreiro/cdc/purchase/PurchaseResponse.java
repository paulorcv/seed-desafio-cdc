package br.com.epermatozoideguerreiro.cdc.purchase;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

public class PurchaseResponse {

    private Long id;

    private String email;

    private String name;

    private String lastName;

    private String document;

    private String address;

    private String complement;

    private String city;

    private PurchaseCountryResponse country;

    private PurchaseStateResponse state;

    private String phoneNumber;

    private String cep;

    private BigDecimal total;

    private BigDecimal totalWithDiscounts;

    private List<PurchaseItemOrderResponse> itemsOrder;

    public PurchaseResponse(Purchase purchase) {

        this.id = purchase.getId();
        this.email = purchase.getEmail();
        this.name = purchase.getName();
        this.lastName = purchase.getLastName();
        this.document = purchase.getDocument();
        this.address = purchase.getAddress();
        this.complement = purchase.getComplement();
        this.city = purchase.getCity();
        this.country = new PurchaseCountryResponse(purchase.getCountry());
        this.state = new PurchaseStateResponse(purchase.getState());
        this.phoneNumber = purchase.getPhoneNumber();
        this.cep = purchase.getCep();
        this.total = purchase.getTotal();
        this.totalWithDiscounts = purchase.getTotalWithDiscounts();

        this.itemsOrder = purchase.getItemsOrder().stream().map(c -> new PurchaseItemOrderResponse(c))
        .collect(Collectors.toList());        


    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public PurchaseCountryResponse getCountry() {
        return country;
    }

    public void setCountry(PurchaseCountryResponse country) {
        this.country = country;
    }

    public PurchaseStateResponse getState() {
        return state;
    }

    public void setState(PurchaseStateResponse state) {
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

    public BigDecimal getTotalWithDiscounts() {
        return totalWithDiscounts;
    }

    public void setTotalWithDiscounts(BigDecimal totalWithDiscounts) {
        this.totalWithDiscounts = totalWithDiscounts;
    }

    public List<PurchaseItemOrderResponse> getItemsOrder() {
        return itemsOrder;
    }

    public void setItemsOrder(List<PurchaseItemOrderResponse> itemsOrder) {
        this.itemsOrder = itemsOrder;
    }

    

}
