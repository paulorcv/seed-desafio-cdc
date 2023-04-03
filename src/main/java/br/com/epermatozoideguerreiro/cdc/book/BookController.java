package br.com.epermatozoideguerreiro.cdc.book;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Validated
public class BookController {

    @Autowired
    private BookRepository bookRepository;

    @PersistenceContext
    EntityManager manager;

    @PostMapping(value = "/api/book")
    @Transactional
    public void create(@Valid @RequestBody NewBookRequest request) {
        manager.persist(request.toModel(manager));
    }

    @GetMapping(value = "/api/books")
    public ResponseEntity<List<BookResponse>> listAllBooks() {
        List<Book> listBooks = (List<Book>) bookRepository.findAll();

        if (listBooks.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        List<BookResponse> booksResponse = listBooks.stream().map(book -> new BookResponse(book))
                .collect(Collectors.toList());

        return ResponseEntity.ok(booksResponse);

    }

    @GetMapping(value = "/api/books/{id}")
    public ResponseEntity<BookResponse> getBook(@PathVariable("id") Long id) {
        Optional<Book> book = bookRepository.findById(id);

        if (book.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        BookResponse bookResponse = new BookResponse(book.get());

        return ResponseEntity.ok(bookResponse);

    }

}
