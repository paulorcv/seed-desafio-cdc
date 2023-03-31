package br.com.epermatozoideguerreiro.cdc.coupon;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class CouponValidator implements Validator {

    @Autowired
    CouponRepository couponRepository;

    @Override
    public boolean supports(Class<?> clazz) {
        return NewCouponRequest.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

        if (errors.hasErrors())
            return;
        NewCouponRequest request = (NewCouponRequest) target;

        Optional<Coupon> coupon = couponRepository.findByCode(request.getCode());

        if (coupon.isPresent()) {
            errors.rejectValue("code", null, "Já existe cupom com este código:" + request.getCode());
        }

    }

}
