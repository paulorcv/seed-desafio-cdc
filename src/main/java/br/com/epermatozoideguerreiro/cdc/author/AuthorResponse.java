package br.com.epermatozoideguerreiro.cdc.author;

public class AuthorResponse {

    private Long id;
    private String name;
    private String email;
    private String description;

    public AuthorResponse(Author author) {
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
