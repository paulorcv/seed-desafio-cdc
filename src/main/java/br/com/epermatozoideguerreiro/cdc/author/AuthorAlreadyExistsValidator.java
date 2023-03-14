package br.com.epermatozoideguerreiro.cdc.author;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Optional;

@Component
class AuthorAlreadyExistsValidator implements Validator {

    @Autowired
    AuthorRepository authorRepository;

    @Override
    public boolean supports(Class<?> clazz) {
        return NewAuthorRequest.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

        if(errors.hasErrors())  return;
        NewAuthorRequest request = (NewAuthorRequest) target;

        Optional<Author> author = authorRepository.findByEmail(request.getEmail());

        if(author.isPresent()) {
            errors.rejectValue("email", null, "Já existe autor com este e-mail:" + request.getEmail());
        }

    }
}



