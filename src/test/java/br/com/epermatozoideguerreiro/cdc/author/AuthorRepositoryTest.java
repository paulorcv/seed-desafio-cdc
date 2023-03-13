package br.com.epermatozoideguerreiro.cdc.author;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class AuthorRepositoryTest {

    @Mock
    AuthorRepository repository;

    @Test
    public void saveAuthorTest(){

        Author author = new Author("teste", "teste", "teste");

        Mockito.when(repository.save(Mockito.any(Author.class)))
                .thenReturn(author);

        Author savedAuthor = repository.save(author);
        Assertions.assertEquals(author.getName(), savedAuthor.getName());
        Assertions.assertEquals(author.getDescription(), savedAuthor.getDescription());

    }


    @Test
    public void findByEmailTest(){

        Author author = new Author("teste", "teste@teste.com", "teste");

        Mockito.when(repository.findByEmail(Mockito.anyString()))
                .thenReturn(Optional.of(author));

        Optional<Author> returnedAuthor = repository.findByEmail(author.getEmail());
        Assertions.assertEquals(author.getName(), returnedAuthor.get().getName());
        Assertions.assertEquals(author.getDescription(), returnedAuthor.get().getDescription());

    }
}
