package br.com.epermatozoideguerreiro.cdc.purchase;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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
    StateBelongsToCountryValidator stateBelongsToCountryValidator;

    @InitBinder
    public void init(WebDataBinder binder) {
        binder.addValidators(stateBelongsToCountryValidator);
    }

    @PostMapping(value = "/api/purchases")
    public String create(@Valid @RequestBody NewPurchaseRequest request) {
        return request.toModel(countryRepository, stateRepository).toString();
    }

}
