package br.com.epermatozoideguerreiro.cdc.author;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
@Validated
public class AuthorController {

    @Autowired
    private AuthorRepository authorRepository;

    @PostMapping(value = "/api/author")
    @Transactional
    public void create(@Valid @RequestBody NewAuthorRequest request) {
        authorRepository.save(request.toModel());
    }

}
