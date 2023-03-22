package br.com.epermatozoideguerreiro.cdc.country;


import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest(classes = CountryRepository.class)
public class CountryRepositoryTest {

    @MockBean
    CountryRepository repository;

    @Test
    public void saveCountryTest(){

        Country country = newCountry();

        Mockito.when(repository.save(Mockito.any(Country.class)))
                .thenReturn(country);

        Country savedCountry = repository.save(country);
        Assertions.assertEquals(country.getName(), savedCountry.getName());
    }

    private Country newCountry() {
        return new Country("País de Teste");
    }

    @Test
    public void findByNameTest(){

        Country country = newCountry();    

        Mockito.when(repository.findByName(Mockito.anyString()))
                .thenReturn(Optional.of(country));

        Optional<Country> returnedCountry = repository.findByName(country.getName());
        Assertions.assertEquals(country.getName(), returnedCountry.get().getName());
    }

}
