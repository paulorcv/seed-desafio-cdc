package br.com.epermatozoideguerreiro.cdc.country;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Validated
class CountryController {

    @Autowired
    CountryRepository countryRepository;

    @GetMapping(value = "/api/countries")
    public ResponseEntity<List<CountryResponse>> listAllCountries() {
            List<Country> listCountries = (List<Country>) countryRepository.findAll();
            
            if(listCountries.isEmpty()) {
                return ResponseEntity.noContent().build();
            }

            List<CountryResponse> contriesResponse = listCountries.stream().map(c -> new CountryResponse(c))
            .collect(Collectors.toList());

            return ResponseEntity.ok(contriesResponse);
   
    }
    @PostMapping(value = "/api/country")
    @Transactional
    public void create(@RequestBody @Valid NewCountryRequest request) {
        countryRepository.save(request.toModel());
    }

}