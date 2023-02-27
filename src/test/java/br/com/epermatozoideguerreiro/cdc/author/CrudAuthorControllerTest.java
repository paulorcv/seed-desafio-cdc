package br.com.epermatozoideguerreiro.cdc.author;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
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

import javax.persistence.EntityManager;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = AuthorController.class)
@AutoConfigureMockMvc
@EnableWebMvc
public class CrudAuthorControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private MockMvc mvc;

    @MockBean
    AuthorService service;

    @BeforeEach
    public void setup() {

        mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

        doNothing().when(service).create(any(Author.class));
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

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
