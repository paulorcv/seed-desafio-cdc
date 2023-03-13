package br.com.epermatozoideguerreiro.cdc.author;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends CrudRepository<Author, Long> {
    public Author findByEmail(String email);
}
