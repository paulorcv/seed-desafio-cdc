package br.com.epermatozoideguerreiro.cdc.category;

import javax.validation.constraints.NotBlank;

import org.springframework.stereotype.Component;

import br.com.epermatozoideguerreiro.cdc.shared.UniqueValue;

@Component
public class NewCategoryRequest {
    @NotBlank
    @UniqueValue(domainClass = Category.class, fieldname = "name")
    private String name;

    public NewCategoryRequest() {
    }

    public NewCategoryRequest(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Category toModel() {
        return new Category(this.name);
    }


}
