package br.com.epermatozoideguerreiro.cdc.purchase;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Validated
public class PurchaseController {

    @PersistenceContext
    EntityManager manager;

    @Autowired
    StateBelongsToCountryValidator stateBelongsToCountryValidator;

    @Autowired
    ItemsOrderValidator itemsOrderValidator;

    @InitBinder
    public void init(WebDataBinder binder) {
        binder.addValidators(stateBelongsToCountryValidator, itemsOrderValidator);
    }

    @PostMapping(value = "/api/purchases")
    @Transactional
    @ResponseBody
    public ResponseEntity<Purchase> create(@Valid @RequestBody NewPurchaseRequest request) {
        Purchase purchase = request.toModel(manager);
        manager.persist(purchase);
        // return new ResponseEntity<>(purchase, HttpStatus.CREATED);
        return ResponseEntity.status(HttpStatus.CREATED).body(purchase);


    }

}
