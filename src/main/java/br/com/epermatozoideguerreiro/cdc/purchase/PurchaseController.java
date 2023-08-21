package br.com.epermatozoideguerreiro.cdc.purchase;

import java.util.Optional;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.epermatozoideguerreiro.cdc.coupon.CouponRepository;

@RestController
@Validated
public class PurchaseController {

    @Autowired
    EntityManager manager;

    @Autowired
    StateBelongsToCountryValidator stateBelongsToCountryValidator;

    @Autowired
    ItemsOrderValidator itemsOrderValidator;

    @Autowired 
    CouponRepository couponRepository;

    @Autowired
    PurchaseRepository purchaseRepository;

    @InitBinder
    public void init(WebDataBinder binder) {
        binder.addValidators(stateBelongsToCountryValidator, itemsOrderValidator);
    }

    @PostMapping(value = "/api/purchases")
    @Transactional
    @ResponseBody
    public ResponseEntity<Purchase> create(@Valid @RequestBody NewPurchaseRequest request) {
        Purchase purchase = request.toModel(manager, couponRepository);
        manager.persist(purchase);
        return ResponseEntity.status(HttpStatus.CREATED).body(purchase);
    }


    @GetMapping(value = "/api/purchases/{id}")
    public ResponseEntity<PurchaseResponse> getPurchase(@PathVariable("id") Long id) {
        Optional<Purchase> purchase = purchaseRepository.findById(id);

        if (purchase.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        PurchaseResponse purchaseRepository = new PurchaseResponse(purchase.get());

        return ResponseEntity.ok(purchaseRepository);

    }    

}
