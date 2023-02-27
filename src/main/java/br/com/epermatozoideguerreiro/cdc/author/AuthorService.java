package br.com.epermatozoideguerreiro.cdc.author;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Service
public class AuthorService {

    @PersistenceContext
    @Autowired
    EntityManager manager;

    public void create(Author author) {
        manager.persist(author);
    }
}
