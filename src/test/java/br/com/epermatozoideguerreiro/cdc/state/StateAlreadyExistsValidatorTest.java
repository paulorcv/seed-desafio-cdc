package br.com.epermatozoideguerreiro.cdc.state;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

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

import br.com.epermatozoideguerreiro.cdc.country.Country;

@SpringBootTest
@ActiveProfiles("test")
public class StateAlreadyExistsValidatorTest {

    @Autowired
    private StateAlreadyExistsValidator validator;

    @MockBean
    private StateRepository repository;

    Errors errors;

    Optional<State> state;

    @BeforeEach
    public void setup() {
        state = Optional.of(newState());
    }
    @Test
    public void duplicateState() throws Exception {

        NewStateRequest newStateRequest = newStateRequest();
        Mockito.when(repository.findByName(Mockito.anyString())).thenReturn(state);
        errors = new BeanPropertyBindingResult(newStateRequest, "");

        validator.validate(newStateRequest, errors);
        assertTrue(errors.hasErrors());

    }

    private NewStateRequest newStateRequest() {
        return new NewStateRequest("País de teste", 1L);
    }
    @Test
    public void successState() throws Exception {

        NewStateRequest newStateRequest = newStateRequest();
        errors = new BeanPropertyBindingResult(newStateRequest, "");

        validator.validate(newStateRequest, errors);
        assertFalse(errors.hasErrors());

    }

    private State newState() {
        Country country = newCountry();
        return new State("País de teste", country);
    }

    private Country newCountry() {
        return new Country("País de teste");
    }    

}
