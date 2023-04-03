package br.com.epermatozoideguerreiro.cdc.coupon;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.validation.constraints.Future;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import br.com.epermatozoideguerreiro.cdc.shared.UniqueValue;

public class NewCouponRequest {

    @NotBlank
    @UniqueValue(domainClass = Coupon.class, fieldname = "code")
    private String code;
    @NotNull
    @Positive
    @Max(100)
    private BigDecimal percentage;
    @NotNull
    @Future
    private LocalDate validity;

    public NewCouponRequest() {
    }

    public NewCouponRequest(String code, BigDecimal percentage, LocalDate validity) {
        this.code = code;
        this.percentage = percentage;
        this.validity = validity;
    }

    public Coupon toModel() {
        return new Coupon(code, percentage, validity);
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public BigDecimal getPercentage() {
        return percentage;
    }

    public void setPercentage(BigDecimal percentage) {
        this.percentage = percentage;
    }

    public LocalDate getValidity() {
        return validity;
    }

    public void setValidity(LocalDate validity) {
        this.validity = validity;
    }

}
