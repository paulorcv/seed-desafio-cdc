package br.com.epermatozoideguerreiro.cdc.state;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.epermatozoideguerreiro.cdc.country.CountryRepository;

@RestController
@Validated
class StateController {

    @Autowired
    StateRepository stateRepository;

    @Autowired
    CountryRepository countryRepository;    

    @Autowired
    StateAlreadyExistsValidator stateAlreadyExistsValidator;

    @InitBinder
    public void init(WebDataBinder binder) {
        binder.addValidators(stateAlreadyExistsValidator);
    }       

    @GetMapping(value = "/api/states")
    public ResponseEntity<List<StateResponse>> listAllStates() {
            List<State> listCountries = (List<State>) stateRepository.findAll();
            
            if(listCountries.isEmpty()) {
                return ResponseEntity.noContent().build();
            }

            List<StateResponse> contriesResponse = listCountries.stream().map(c -> new StateResponse(c))
            .collect(Collectors.toList());

            return ResponseEntity.ok(contriesResponse);
   
    }
    @PostMapping(value = "/api/state")
    @Transactional
    public void create(@RequestBody @Valid NewStateRequest request) {
        stateRepository.save(request.toModel(countryRepository));
    }

}