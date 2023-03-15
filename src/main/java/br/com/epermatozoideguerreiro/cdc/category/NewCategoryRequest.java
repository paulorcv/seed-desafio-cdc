package br.com.epermatozoideguerreiro.cdc.category;

import javax.validation.constraints.NotBlank;

public class NewCategoryRequest {
    @NotBlank
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
