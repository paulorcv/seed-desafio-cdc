package br.com.epermatozoideguerreiro.cdc.author;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import br.com.epermatozoideguerreiro.cdc.shared.UniqueValue;

public class NewAuthorRequest {

    @NotBlank
    private String name;

    @NotBlank
    @Email
    @UniqueValue(domainClass = Author.class, fieldname = "email")
    private String email;

    @NotBlank
    @Size(max = 400)
    private String description;
    public NewAuthorRequest() {

    }

    public NewAuthorRequest(String name, String email, String description) {
        this.name = name;
        this.email = email;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Author toModel() {
        return new Author(name, email, description);
    }
}
