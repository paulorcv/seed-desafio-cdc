package br.com.epermatozoideguerreiro.cdc.book;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.EntityManager;
import javax.validation.constraints.Future;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.util.Assert;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

import br.com.epermatozoideguerreiro.cdc.author.Author;
import br.com.epermatozoideguerreiro.cdc.category.Category;
import br.com.epermatozoideguerreiro.cdc.shared.ExistsByField;
import br.com.epermatozoideguerreiro.cdc.shared.UniqueValue;

public class NewBookRequest {

    @NotBlank
    @UniqueValue(domainClass = Book.class, fieldname = "title")
    private String title;

    @NotBlank
    @Size(max = 500)
    private String description;

    private String summary;

    @NotNull
    @Min(20)
    private BigDecimal price;

    @NotNull
    @Min(100)
    private int pages;

    @NotBlank
    @UniqueValue(domainClass = Book.class, fieldname = "isbn")
    private String isbn;

    @Future
    @NotNull
    @JsonFormat(pattern = "dd/MM/yyyy", shape = Shape.STRING)
    private LocalDate publicationDate;

    @NotNull
    @ExistsByField(domainClass = Author.class, fieldname = "id")
    private Long idAuthor;

    @NotNull
    @ExistsByField(domainClass = Category.class, fieldname = "id")
    private Long idCategory;

    public NewBookRequest() {
    }

    public NewBookRequest(@NotBlank String title, @NotBlank @Size(max = 500) String description, String summary,
            @NotBlank @Min(20) BigDecimal price, @NotBlank @Min(100) int pages, String isbn,
            @Future LocalDate publicationDate, @NotBlank Long idAuthor, @NotBlank Long idCategory) {
        this.title = title;
        this.description = description;
        this.summary = summary;
        this.price = price;
        this.pages = pages;
        this.isbn = isbn;
        this.publicationDate = publicationDate;
        this.idAuthor = idAuthor;
        this.idCategory = idCategory;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public LocalDate getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(LocalDate publicationDate) {
        this.publicationDate = publicationDate;
    }


    public Long getIdAuthor() {
        return idAuthor;
    }

    public Long getIdCategory() {
        return idCategory;
    }    

    public void setIdAuthor(Long idAuthor) {
        this.idAuthor = idAuthor;
    }

    public void setIdCategory(Long idCategory) {
        this.idCategory = idCategory;
    }

    public Book toModel(EntityManager manager) {

        Category category = manager.find(Category.class, idCategory);
        Author author = manager.find(Author.class, idAuthor);

        Assert.state(author!=null, "Não existe o author com o id: " + idAuthor + " no banco");
        Assert.state(category!=null, "Não existe categoria com o id: " + idCategory + " no banco");

        return new Book(
                title,
                description,
                summary,
                price,
                pages,
                isbn,
                publicationDate,
                author,
                category);
    }

}
