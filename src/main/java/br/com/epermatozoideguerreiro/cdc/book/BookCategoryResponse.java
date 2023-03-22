package br.com.epermatozoideguerreiro.cdc.book;

import br.com.epermatozoideguerreiro.cdc.category.Category;

public class BookCategoryResponse {

    private Long id;
    private String name;

    public BookCategoryResponse(Category category) {
        this.id = category.getId();
        this.name = category.getName();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

}
