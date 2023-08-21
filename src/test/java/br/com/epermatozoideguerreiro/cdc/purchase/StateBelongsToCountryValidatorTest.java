package br.com.epermatozoideguerreiro.cdc.purchase;

import static org.junit.jupiter.api.Assertions.assertFalse;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

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
import br.com.epermatozoideguerreiro.cdc.country.CountryRepository;
import br.com.epermatozoideguerreiro.cdc.state.State;
import br.com.epermatozoideguerreiro.cdc.state.StateRepository;

@SpringBootTest
@ActiveProfiles("test")
public class StateBelongsToCountryValidatorTest {

    @Autowired
    private StateBelongsToCountryValidator validator;

    @MockBean
    private CountryRepository countryRepository;

    @MockBean
    private StateRepository stateRepository;

    Errors errors;

    private Country country;
    private State state;

    @BeforeEach
    public void setup() {
        country = newCountry();
        // book = Optional.of(newBook(author, category));
    }

    @Test
    public void newPurchaseCountryWithoutStates() throws Exception {

        NewPurchaseRequest newPurchaseRequest = newPurchaseRequestWithoutState();
        Mockito.when(countryRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(country));
        errors = new BeanPropertyBindingResult(newPurchaseRequest, "");

        validator.validate(newPurchaseRequest, errors);
        assertFalse(errors.hasErrors());

    }

    @Test
    public void newPurchaseCountryWithtStates() throws Exception {

        state = newState(country);
        List<State> states = new ArrayList<State>();
        states.add(state);
        country.setStates(states);
        Mockito.when(stateRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(state));

        NewPurchaseRequest newPurchaseRequest = newPurchaseRequestWithState();
        Mockito.when(countryRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(country));
        errors = new BeanPropertyBindingResult(newPurchaseRequest, "");

        validator.validate(newPurchaseRequest, errors);
        assertFalse(errors.hasErrors());

    }

    private NewPurchaseRequest newPurchaseRequestWithState() {
        return new NewPurchaseRequest("teste@teste.com", "Nome de teste", "Sobrenome", "04951442663",
                "endereço qualquer", "complemento qualquer", "Cidade Qualquer", 1L, 1L, "123113131", "313131", BigDecimal.valueOf(10), newItemsOrderRequests());
    }

    private @NotEmpty List<@Valid ItemOrderRequest> newItemsOrderRequests() {
        List<ItemOrderRequest> items = new ArrayList<ItemOrderRequest>();
        items.add(newItemOrder());
        items.add(newItemOrder());
        return items;
    }

    private ItemOrderRequest newItemOrder() {
        return new ItemOrderRequest(1L, 1,  BigDecimal.valueOf(10));
    }

    private NewPurchaseRequest newPurchaseRequestWithoutState() {
        return new NewPurchaseRequest("teste@teste.com", "Nome de teste", "Sobrenome", "04951442663",
                "endereço qualquer", "complemento qualquer", "Cidade Qualquer", 1L, null, "123113131", "313131", BigDecimal.valueOf(10), newItemsOrderRequests());
    }

    private Country newCountry() {
        return new Country("País qualquer");
    }

    private State newState(Country country) {
        return new State("Estado de teste", country);
    }
}
