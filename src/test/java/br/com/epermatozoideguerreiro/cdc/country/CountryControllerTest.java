package br.com.epermatozoideguerreiro.cdc.country;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJson;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import br.com.epermatozoideguerreiro.cdc.author.Author;
import br.com.epermatozoideguerreiro.cdc.category.Category;

@SpringBootTest(classes = CountryController.class)
@AutoConfigureMockMvc
@AutoConfigureJson
@EnableWebMvc
@ActiveProfiles("test")
class CountryControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;
    @Autowired
    private MockMvc mvc;

    @MockBean
    CountryRepository countryRepository;

    @MockBean
    CountryAlreadyExistsValidator countryAlreadyExistsValidator;

    @MockBean
    EntityManager manager;


    @BeforeEach
    public void setup() {

        mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        Country country = newCountry();

        Mockito.when(countryRepository.save(Mockito.any(Country.class)))
                .thenReturn(country);

        Mockito.when(countryAlreadyExistsValidator.supports(NewCountryRequest.class)).thenReturn(true);

        Mockito.when(manager.createQuery(Mockito.anyString())).thenReturn(Mockito.mock(Query.class));

    }

    @Test
    void createCountrySuccess() throws Exception {

        NewCountryRequest form = newCountryRequest();

        mvc.perform(
                post("/api/country")
                        .content(asJsonString(form))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }

    @Test
    void createCountryWithoutName() throws Exception {

        NewCountryRequest form = newCountryWithouName();

        mvc.perform(
                post("/api/country")
                        .content(asJsonString(form))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());

    }



@Test
    void listCountrysSucess() throws Exception {

        Country country1 = newCountry();
        Country country2 = newCountry();
        List<Country> listCountrys = Arrays.asList(country1, country2);

        when(countryRepository.findAll()).thenReturn(listCountrys);

        mvc.perform(
                get("/api/countries")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }

    @Test
    void listCountrysEmpyty() throws Exception {

        List<Country> listCountrys = new ArrayList<Country>();

        when(countryRepository.findAll()).thenReturn(listCountrys);

        mvc.perform(
                get("/api/countries")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
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

    private Country newCountry() {
        return new Country("País de teste");
    }

    private NewCountryRequest newCountryWithouName() {
        return new NewCountryRequest("");
}

    private Category newCategory() {
        return new Category("teste");
    }

    private Author newAuthor() {
        return new Author("autor", "teste@teste.com", "descrição");
    }

    private NewCountryRequest newCountryRequest() {
        return new NewCountryRequest("País de teste");
    }


}