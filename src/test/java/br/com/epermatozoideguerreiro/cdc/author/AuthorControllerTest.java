package br.com.epermatozoideguerreiro.cdc.author;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = AuthorController.class)
@AutoConfigureMockMvc
@EnableWebMvc
public class AuthorControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private MockMvc mvc;

    @MockBean
    AuthorRepository repository;

    @BeforeEach
    public void setup() {

        mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();


        Author author = new Author("teste", "teste@teste.com", "teste");

        Mockito.when(repository.save(Mockito.any(Author.class)))
                .thenReturn(author);
    }

    @Test
    void createAuthorSuccess() throws Exception {

        NewAuthorRequest form = new NewAuthorRequest();
        form.setName("Nome do Autor");
        form.setEmail("teste@teste.com");
        form.setDescription("Descrição do autor");

        mvc.perform(
                post("/api/author")
                        .content(asJsonString(form))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk());

    }

    @Test
    void createAuthorWithoutName() throws Exception {

        NewAuthorRequest form = new NewAuthorRequest();
        form.setEmail("teste@teste.com");
        form.setDescription("Descrição do autor");

        mvc.perform(
                post("/api/author")
                        .content(asJsonString(form))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
        ).andExpect(status().isBadRequest());

    }

    @Test
    void createAuthorWithoutDescription() throws Exception {

        NewAuthorRequest form = new NewAuthorRequest();
        form.setName("Teste");
        form.setEmail("teste@teste.com");

        mvc.perform(
                post("/api/author")
                        .content(asJsonString(form))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
        ).andExpect(status().isBadRequest());

    }

    @Test
    void createAuthorWithInvalidEmail() throws Exception {

        NewAuthorRequest form = new NewAuthorRequest();
        form.setName("Teste");
        form.setEmail("teste");
        form.setDescription("Teste");

        mvc.perform(
                post("/api/author")
                        .content(asJsonString(form))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
        ).andExpect(status().isBadRequest());

    }

    @Test
    void createDuplicateAuthor() throws Exception {

        Author author = new Author("teste", "teste@teste.com", "Autor que já existe");

        Mockito.when(repository.findByEmail(Mockito.anyString()))
                .thenReturn(author);

        NewAuthorRequest form = new NewAuthorRequest();
        form.setName("Teste");
        form.setEmail("teste@teste.com");
        form.setDescription("Teste");

            mvc.perform(
                    post("/api/author")
                            .content(asJsonString(form))
                            .contentType(MediaType.APPLICATION_JSON)
                            .accept(MediaType.APPLICATION_JSON)
            ).andExpect(status().isNotAcceptable());
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
