package br.com.epermatozoideguerreiro.cdc.purchase;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.epermatozoideguerreiro.cdc.book.BookRepository;
import br.com.epermatozoideguerreiro.cdc.country.CountryRepository;
import br.com.epermatozoideguerreiro.cdc.state.StateRepository;

@RestController
@Validated
public class PurchaseController {

    @Autowired
    CountryRepository countryRepository;

    @Autowired
    StateRepository stateRepository;

    @Autowired
    PurchaseRepository purchaseRepository;

    @Autowired
    BookRepository bookRepository;

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
    public void create(@Valid @RequestBody NewPurchaseRequest request) {
        purchaseRepository.save(request.toModel(countryRepository, stateRepository, bookRepository));
    }

}
