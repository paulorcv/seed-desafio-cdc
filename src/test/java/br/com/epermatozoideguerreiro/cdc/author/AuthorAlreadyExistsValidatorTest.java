package br.com.epermatozoideguerreiro.cdc.author;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class AuthorAlreadyExistsValidatorTest {

    @Autowired
    private AuthorAlreadyExistsValidator validator;

    @MockBean
    private AuthorRepository authorRepository;

    Errors errors;

    Optional<Author> author;

    @BeforeEach
    public void setup() {
        author = Optional.of(new Author("teste", "teste@teste.com", "Autor que já existe"));
    }
    @Test
    public void duplicateAuthor() throws Exception {

        NewAuthorRequest newAuthorRequest = new NewAuthorRequest("teste", "teste@teste.com", "Autor que já existe");
        Mockito.when(authorRepository.findByEmail(Mockito.anyString())).thenReturn(author);
        errors = new BeanPropertyBindingResult(newAuthorRequest, "");

        validator.validate(newAuthorRequest, errors);
        assertTrue(errors.hasErrors());

    }

    @Test
    public void successAuthor() throws Exception {

        NewAuthorRequest newAuthorRequest = new NewAuthorRequest("teste", "teste@teste.com", "Autor que já existe");
        errors = new BeanPropertyBindingResult(newAuthorRequest, "");

        validator.validate(newAuthorRequest, errors);
        assertFalse(errors.hasErrors());

    }


}
