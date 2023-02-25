package br.com.epermatozoideguerreiro.cdc;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping(value = "/api/test")
    public String teste() {
        return "Tá tudo ok meu caro!";
    }

}
