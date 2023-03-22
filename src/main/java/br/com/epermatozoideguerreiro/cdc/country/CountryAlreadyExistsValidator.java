package br.com.epermatozoideguerreiro.cdc.country;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class CountryAlreadyExistsValidator implements Validator {

    @Autowired
    CountryRepository countryRepository;

    @Override
    public boolean supports(Class<?> clazz) {
        return NewCountryRequest.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

        if(errors.hasErrors())  return;
        NewCountryRequest request = (NewCountryRequest) target;

        Optional<Country> country = countryRepository.findByName(request.getName());

        if(country.isPresent()) {
            errors.rejectValue("name", null, "Já existe país com este nome:" + request.getName());
        }

    }    

}
