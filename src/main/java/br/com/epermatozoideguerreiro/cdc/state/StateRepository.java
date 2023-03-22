package br.com.epermatozoideguerreiro.cdc.state;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StateRepository extends CrudRepository<State, Long>{

    Optional<State> findByName(String name);

}
