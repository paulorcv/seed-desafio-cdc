package br.com.epermatozoideguerreiro.cdc.coupon;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Embeddable;
import javax.validation.constraints.Future;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Embeddable
public class CouponApplied {

    @NotBlank
    private String code;
    @NotNull
    @Positive
    @Max(100)
    private BigDecimal percentage;
    @NotNull
    @Future
    private LocalDate validity;

    public CouponApplied() {
    }

    public CouponApplied(String code, BigDecimal percentage, LocalDate validity) {
        this.code = code;
        this.percentage = percentage;
        this.validity = validity;
    }

    public CouponApplied(Coupon coupon) {
        if (coupon != null) {
            this.code = coupon.getCode();
            this.percentage = coupon.getPercentage();
            this.validity = coupon.getValidity();
        }
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

    public boolean isValid() {
        return this.validity.isAfter(LocalDate.now());
    }

}
