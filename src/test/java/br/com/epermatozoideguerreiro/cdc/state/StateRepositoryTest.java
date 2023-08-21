package br.com.epermatozoideguerreiro.cdc.state;


import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import br.com.epermatozoideguerreiro.cdc.country.Country;

@SpringBootTest(classes = StateRepository.class)
@ActiveProfiles("test")
public class StateRepositoryTest {

    @MockBean
    StateRepository repository;

    @Test
    public void saveStateTest(){

        Country country = newCountry();
        State state = newState(country);

        Mockito.when(repository.save(Mockito.any(State.class)))
                .thenReturn(state);

        State savedState = repository.save(state);
        Assertions.assertEquals(state.getName(), savedState.getName());
    }

    private State newState(Country country) {
        return new State("Estado de Teste", country);
    }

    private Country newCountry() {
        return new Country("País de Teste");
    }    

    @Test
    public void findByNameTest(){
        Country country = newCountry();
        State state = newState(country);    

        Mockito.when(repository.findByName(Mockito.anyString()))
                .thenReturn(Optional.of(state));

        Optional<State> returnedState = repository.findByName(state.getName());
        Assertions.assertEquals(state.getName(), returnedState.get().getName());
    }

}
