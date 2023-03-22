package br.com.epermatozoideguerreiro.cdc.book;

import br.com.epermatozoideguerreiro.cdc.author.Author;

public class BookAuthorResponse {

    private Long id;
    private String name;
    private String email;
    private String description;

    public BookAuthorResponse(Author author) {
        this.id = author.getId();
        this.name = author.getName();
        this.email = author.getEmail();
        this.description = author.getDescription();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getDescription() {
        return description;
    }

}
