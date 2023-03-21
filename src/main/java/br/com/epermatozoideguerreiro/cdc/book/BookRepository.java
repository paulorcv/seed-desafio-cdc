package br.com.epermatozoideguerreiro.cdc.book;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends CrudRepository<Book, Long> {
    Optional<Book> findByTitle(String title);
    Optional<Book> findByIsbn(String isbn);
}
