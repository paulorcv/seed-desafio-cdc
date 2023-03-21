package br.com.epermatozoideguerreiro.cdc.book;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
@Validated
public class BookController {

    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private BookAlreadyExistsValidator bookAlreadyExistsValidator;

    @PersistenceContext
    EntityManager manager;

    @InitBinder
    public void init(WebDataBinder binder) {
        binder.addValidators(bookAlreadyExistsValidator);
    }

    @PostMapping(value = "/api/book")
    @Transactional
    public void create(@Valid @RequestBody NewBookRequest request) {
        bookRepository.save(request.toModel(manager));
    }

}
