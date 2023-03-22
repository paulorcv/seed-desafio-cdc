package br.com.epermatozoideguerreiro.cdc.state;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class StateAlreadyExistsValidator implements Validator {

    @Autowired
    StateRepository stateRepository;

    @Override
    public boolean supports(Class<?> clazz) {
        return NewStateRequest.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

        if(errors.hasErrors())  return;
        NewStateRequest request = (NewStateRequest) target;

        Optional<State> country = stateRepository.findByName(request.getName());

        if(country.isPresent()) {
            errors.rejectValue("name", null, "Já existe estado com este nome:" + request.getName());
        }

    }   

}
