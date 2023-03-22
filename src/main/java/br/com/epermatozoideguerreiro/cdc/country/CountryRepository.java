package br.com.epermatozoideguerreiro.cdc.country;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CountryRepository extends CrudRepository<Country, Long>{

    Optional<Country> findByName(String name);
    
}
