package br.com.epermatozoideguerreiro.cdc.book;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;

import br.com.epermatozoideguerreiro.cdc.author.Author;
import br.com.epermatozoideguerreiro.cdc.category.Category;

@SpringBootTest
@ActiveProfiles("test")
public class BookAlreadyExistsValidatorTest {

    @Autowired
    private BookAlreadyExistsValidator validator;

    @MockBean
    private BookRepository repository;

    Errors errors;

    Optional<Book> book;

    @BeforeEach
    public void setup() {
        Category category = newCategory();
        Author author = new Author();
        book = Optional.of(newBook(author, category));
    }
    @Test
    public void duplicateBook() throws Exception {

        NewBookRequest newBookRequest = newBookRequest();
        Mockito.when(repository.findByTitle(Mockito.anyString())).thenReturn(book);
        Mockito.when(repository.findByIsbn(Mockito.anyString())).thenReturn(book);
        errors = new BeanPropertyBindingResult(newBookRequest, "");

        validator.validate(newBookRequest, errors);
        assertTrue(errors.hasErrors());

    }

    private NewBookRequest newBookRequest() {
        return new NewBookRequest("titulo", "descrição", "sumário", new BigDecimal(21), 110, "2321", LocalDate.of(2200, 10, 10), 12L, 12L);
    }
    @Test
    public void successBook() throws Exception {

        NewBookRequest newBookRequest = newBookRequest();
        errors = new BeanPropertyBindingResult(newBookRequest, "");

        validator.validate(newBookRequest, errors);
        assertFalse(errors.hasErrors());

    }


    private Book newBook(Author author, Category category) {
        return new Book("titulo", "descrição", "sumário", new BigDecimal(21), 110, "123", LocalDate.of(2110, 10, 10), author, category);
    }


    private Category newCategory() {
        return new Category("teste");
    }


    private Author newAuthor() {
        return new Author("autor", "teste@teste.com", "descrição");
    }    

}
