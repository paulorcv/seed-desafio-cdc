package br.com.epermatozoideguerreiro.cdc.book;

import java.util.List;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.epermatozoideguerreiro.cdc.author.AuthorRepository;
import br.com.epermatozoideguerreiro.cdc.category.CategoryRepository;


@RestController
@Validated
public class BookController {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;
    
    @Autowired 
    private CategoryRepository categoryRepository;

    @Autowired
    private BookAlreadyExistsValidator bookAlreadyExistsValidator;

    @InitBinder
    public void init(WebDataBinder binder) {
        binder.addValidators(bookAlreadyExistsValidator);
    }

    @PostMapping(value = "/api/book")
    @Transactional
    public void create(@Valid @RequestBody NewBookRequest request) {
        bookRepository.save(request.toModel(authorRepository, categoryRepository));
    }

    @GetMapping(value = "/api/books")
    public List<Book> listAllBooks() {
        return (List<Book>) bookRepository.findAll();
    }


}
