package br.com.epermatozoideguerreiro.cdc.book;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Optional;

@Component
class BookAlreadyExistsValidator implements Validator {

    @Autowired
    BookRepository bookRepository;

    @Override
    public boolean supports(Class<?> clazz) {
        return NewBookRequest.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

        if(errors.hasErrors())  return;
        NewBookRequest request = (NewBookRequest) target;

        Optional<Book> book = bookRepository.findByTitle(request.getTitle());

        if(book.isPresent()) {
            errors.rejectValue("title", null, "Já existe livro com este título:" + request.getTitle());
        }

        book = bookRepository.findByIsbn(request.getIsbn());

        if(book.isPresent()) {
            errors.rejectValue("isbn", null, "Já existe livro com este isbn:" + request.getIsbn());
        }        

    }
}



