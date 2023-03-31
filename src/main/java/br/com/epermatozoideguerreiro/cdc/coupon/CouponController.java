package br.com.epermatozoideguerreiro.cdc.coupon;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CouponController {

    @Autowired
    CouponRepository couponRepository;

    @Autowired
    CouponValidator couponValidator;

    @InitBinder
    public void init(WebDataBinder binder) {
        binder.addValidators(couponValidator);
    }

    @PostMapping(value = "/api/coupons")
    @Transactional
    public ResponseEntity<Coupon> create(@RequestBody @Valid NewCouponRequest request) {
        Coupon savedItem = couponRepository.save(request.toModel());
        return new ResponseEntity<>(savedItem, HttpStatus.CREATED);
    }

}
