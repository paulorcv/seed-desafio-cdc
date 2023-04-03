package br.com.epermatozoideguerreiro.cdc.purchase;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.Valid;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import org.springframework.util.Assert;

import br.com.epermatozoideguerreiro.cdc.country.Country;
import br.com.epermatozoideguerreiro.cdc.coupon.CouponApplied;
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
    @Positive
    private BigDecimal total;

    private BigDecimal totalWithDiscounts;

    @NotEmpty
    @OneToMany(cascade = CascadeType.ALL)
    private List<@Valid ItemOrder> itemsOrder;

    @Embedded
    private CouponApplied couponApplied;

    public Purchase() {
    }

    public Purchase(@NotBlank @Email String email, @NotBlank String name, @NotBlank String lastName,
            @NotBlank @Documento String document, @NotBlank String address, @NotBlank String complement,
            @NotBlank String city, @NotNull Country country, State state, @NotBlank String phoneNumber,
            @NotBlank String cep, @NotNull @Positive BigDecimal total, @NotEmpty List<@Valid ItemOrder> itemsOrder) {
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

    private void applyDiscount() {
        BigDecimal discount = BigDecimal.ZERO;
        if (this.couponApplied != null) {
            discount = this.getTotal().multiply(couponApplied.getPercentage()).divide(BigDecimal.valueOf(100));
        }
        this.totalWithDiscounts = this.getTotal().subtract(discount);
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

    public BigDecimal getTotalWithDiscounts() {
        return totalWithDiscounts;
    }

    public void setTotalWithDiscounts(BigDecimal totalWithDiscounts) {
        this.applyDiscount();
    }

    

    public CouponApplied getCouponApplied() {
        return couponApplied;
    }

    public void setCouponApplied(CouponApplied couponApplied) {
        Assert.isNull(this.getCouponApplied(), "Tentando trocar o cupom de uma compra");

        this.couponApplied = couponApplied;
        this.applyDiscount();
    }

    public Purchase(Long id, @NotBlank @Email String email, @NotBlank String name, @NotBlank String lastName,
            @NotBlank @Documento String document, @NotBlank String address, @NotBlank String complement,
            @NotBlank String city, @NotNull Country country, State state, @NotBlank String phoneNumber,
            @NotBlank String cep, @NotNull @Positive BigDecimal total, @NotEmpty List<@Valid ItemOrder> itemsOrder) {
        this.id = id;
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

}
