package br.com.epermatozoideguerreiro.cdc.category;

import br.com.epermatozoideguerreiro.cdc.author.NewAuthorRequest;
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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = CategoryController.class)
@AutoConfigureMockMvc
@EnableWebMvc
class CategoryControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;
    @Autowired
    private MockMvc mvc;

    @MockBean
    CategoryRepository categoryRepository;

    @MockBean
    CategoryAlreadyExistsValidator categoryAlreadyExistsValidator;


    @BeforeEach
    public void setup() {

        mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

        Category category = new Category("teste de categoria");

        Mockito.when(categoryRepository.save(Mockito.any(Category.class)))
                .thenReturn(category);

        Mockito.when(categoryAlreadyExistsValidator.supports(NewCategoryRequest.class)).thenReturn(true);
    }
    @Test
    void createCategorySuccess() throws Exception {

        NewCategoryRequest form = new NewCategoryRequest("Categoria1");

        mvc.perform(
                post("/api/category")
                        .content(asJsonString(form))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk());

    }


    @Test
    void createCategoryWithoutName() throws Exception {

        NewCategoryRequest form = new NewCategoryRequest();

        mvc.perform(
                post("/api/category")
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