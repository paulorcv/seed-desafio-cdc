package br.com.epermatozoideguerreiro.cdc.coupon;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CouponRepository extends CrudRepository<Coupon, Long> {

    Optional<Coupon> findByCode(String code);

}
