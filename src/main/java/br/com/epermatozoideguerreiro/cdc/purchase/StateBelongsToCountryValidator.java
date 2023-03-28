package br.com.epermatozoideguerreiro.cdc.purchase;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import br.com.epermatozoideguerreiro.cdc.country.Country;
import br.com.epermatozoideguerreiro.cdc.country.CountryRepository;
import br.com.epermatozoideguerreiro.cdc.state.State;
import br.com.epermatozoideguerreiro.cdc.state.StateRepository;

@Component
public class StateBelongsToCountryValidator implements Validator {

    @Autowired
    CountryRepository countryRepository;

    @Autowired
    StateRepository stateRepository;

    @Override
    public boolean supports(Class<?> clazz) {
        return NewPurchaseRequest.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        if (errors.hasErrors())
            return;

        NewPurchaseRequest request = (NewPurchaseRequest) target;

        Optional<Country> countryOptional = countryRepository.findById(request.getIdCountry());

        if (countryOptional.isEmpty()) {
            errors.rejectValue("idCountry", null, "não existe país com este id");
        } else {
            Country country = countryOptional.get();

            if (country.hasStates()) {
                Optional<State> stateOptional = stateRepository.findById(request.getIdState());
                if (stateOptional.isEmpty()) {
                    errors.rejectValue("idState", null, "não existe estado com este id");
                } else {
                    State state = stateOptional.get();
                    if (!state.belongsToCountry(countryOptional.get())) {
                        errors.rejectValue("idState", null, "esse estado não pertence a este país");
                    }
                }
            }else {
                if(request.getIdState() != null ) {
                    errors.rejectValue("idState", null, "informado um estado para um país que não posssui estados");
                }
            }

        }

    }

}
