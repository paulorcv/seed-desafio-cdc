package br.com.epermatozoideguerreiro.cdc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(enableDefaultTransactions = false)
public class CasaDoCodigoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CasaDoCodigoApplication.class, args);
	}

}
