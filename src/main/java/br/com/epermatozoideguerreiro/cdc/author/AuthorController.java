package br.com.epermatozoideguerreiro.cdc.author;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;


@RestController
@Validated
public class AuthorController {

    @Autowired
    private AuthorRepository authorRepository;
    @Autowired
    private AuthorAlreadyExistsValidator authorAlreadyExistsValidator;

    @InitBinder
    public void init(WebDataBinder binder) {
        binder.addValidators(authorAlreadyExistsValidator);
    }

    @PostMapping(value = "/api/author")
    @Transactional
    public void create(@Valid @RequestBody NewAuthorRequest request) {
        authorRepository.save(request.toModel());
    }

}
