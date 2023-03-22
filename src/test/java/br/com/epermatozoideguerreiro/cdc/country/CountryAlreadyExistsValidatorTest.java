package br.com.epermatozoideguerreiro.cdc.country;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;

@SpringBootTest
public class CountryAlreadyExistsValidatorTest {

    @Autowired
    private CountryAlreadyExistsValidator validator;

    @MockBean
    private CountryRepository repository;

    Errors errors;

    Optional<Country> country;

    @BeforeEach
    public void setup() {
        country = Optional.of(newCountry());
    }
    @Test
    public void duplicateCountry() throws Exception {

        NewCountryRequest newCountryRequest = newCountryRequest();
        Mockito.when(repository.findByName(Mockito.anyString())).thenReturn(country);
        errors = new BeanPropertyBindingResult(newCountryRequest, "");

        validator.validate(newCountryRequest, errors);
        assertTrue(errors.hasErrors());

    }

    private NewCountryRequest newCountryRequest() {
        return new NewCountryRequest("País de teste");
    }
    @Test
    public void successCountry() throws Exception {

        NewCountryRequest newCountryRequest = newCountryRequest();
        errors = new BeanPropertyBindingResult(newCountryRequest, "");

        validator.validate(newCountryRequest, errors);
        assertFalse(errors.hasErrors());

    }

    private Country newCountry() {
        return new Country("País de teste");
    }


}
