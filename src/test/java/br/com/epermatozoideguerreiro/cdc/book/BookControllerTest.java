package br.com.epermatozoideguerreiro.cdc.book;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJson;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import br.com.epermatozoideguerreiro.cdc.author.Author;
import br.com.epermatozoideguerreiro.cdc.author.AuthorRepository;
import br.com.epermatozoideguerreiro.cdc.category.Category;
import br.com.epermatozoideguerreiro.cdc.category.CategoryRepository;

@SpringBootTest(classes = BookController.class)
@AutoConfigureMockMvc
@AutoConfigureJson
@EnableWebMvc
class BookControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;
    @Autowired
    private MockMvc mvc;

    @MockBean
    BookRepository bookRepository;

    @MockBean
    AuthorRepository authorRepository;

    @MockBean
    CategoryRepository categoryRepository;

    @MockBean
    BookAlreadyExistsValidator bookAlreadyExistsValidator;

    @BeforeEach
    public void setup() {

        mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        Author author = newAuthor();
        Category category = newCategory();
        Book book = newBook(author, category);

        Mockito.when(bookRepository.save(Mockito.any(Book.class)))
                .thenReturn(book);
        
        Mockito.when(authorRepository.findById(Mockito.any())).thenReturn(Optional.of(newAuthor()));
        Mockito.when(categoryRepository.findById(Mockito.any())).thenReturn(Optional.of(newCategory()));

        Mockito.when(bookAlreadyExistsValidator.supports(NewBookRequest.class)).thenReturn(true);
    }

    @Test
    void createBookSuccess() throws Exception {

        NewBookRequest form = newBookRequest();

        mvc.perform(
                post("/api/book")
                        .content(asJsonString(form))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }

    @Test
    void createBookWithoutInsuficientPages() throws Exception {

        NewBookRequest form = newBookInvalidPagesRequest();

        mvc.perform(
                post("/api/book")
                        .content(asJsonString(form))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());

    }

    @Test
    void createBookWithoutNoTitle() throws Exception {

        NewBookRequest form = newBookInvalidTitleRequest();

        mvc.perform(
                post("/api/book")
                        .content(asJsonString(form))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());

    }    


    @Test
    void createBookWithPublicationDateInThePast() throws Exception {

        NewBookRequest form = newBookInvalidPublicationDateRequest();

        mvc.perform(
                post("/api/book")
                        .content(asJsonString(form))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());

    }            

    public static String asJsonString(final Object obj) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.registerModule(new JavaTimeModule());            
            return objectMapper.writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
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

    private NewBookRequest newBookRequest() {
        return new NewBookRequest("titulo", "descrição", "sumário", new BigDecimal(21), 110, "2321", LocalDate.of(2100, 10, 10), 12L, 12L);
    }

    private NewBookRequest newBookInvalidPagesRequest() {
        return new NewBookRequest("titulo", "descrição", "sumário", new BigDecimal(21), 19, "2321", LocalDate.of(2100, 10, 10), 12L, 12L);
    }    


    private NewBookRequest newBookInvalidTitleRequest() {
        return new NewBookRequest("", "descrição", "sumário", new BigDecimal(21), 19, "2321", LocalDate.of(2100, 10, 10), 12L, 12L);
    }       
    
    private NewBookRequest newBookInvalidPublicationDateRequest() {
        return new NewBookRequest("", "descrição", "sumário", new BigDecimal(21), 19, "2321", LocalDate.of(2020, 10, 10), 12L, 12L);
    }       

}