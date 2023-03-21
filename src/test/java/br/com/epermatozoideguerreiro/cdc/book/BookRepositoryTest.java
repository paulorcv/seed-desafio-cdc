package br.com.epermatozoideguerreiro.cdc.book;


import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import br.com.epermatozoideguerreiro.cdc.author.Author;
import br.com.epermatozoideguerreiro.cdc.category.Category;

@SpringBootTest(classes = BookRepository.class)
public class BookRepositoryTest {

    @MockBean
    BookRepository repository;

    @Test
    public void saveCategoryTest(){

        Author author = newAuthor();
        Category category = newCategory();
        Book book = newBook(author, category);

        Mockito.when(repository.save(Mockito.any(Book.class)))
                .thenReturn(book);

        Book savedBook = repository.save(book);
        Assertions.assertEquals(book.getTitle(), savedBook.getTitle());
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


    @Test
    public void findByIsbnTest(){
        Category category = newCategory();
        Author author = newAuthor();
        Book book = newBook(author, category);

        Mockito.when(repository.findByIsbn(Mockito.anyString()))
                .thenReturn(Optional.of(book));

        Optional<Book> returnedBook = repository.findByIsbn(book.getIsbn());
        Assertions.assertEquals(book.getIsbn(), returnedBook.get().getIsbn());
    }

    @Test
    public void findByTitleTest(){
        Category category = newCategory();
        Author author = newAuthor();
        Book book = newBook(author, category);

        Mockito.when(repository.findByTitle(Mockito.anyString()))
                .thenReturn(Optional.of(book));

        Optional<Book> returnedBook = repository.findByTitle(book.getTitle());
        Assertions.assertEquals(book.getTitle(), returnedBook.get().getTitle());
    } 
}
