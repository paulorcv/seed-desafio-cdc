package br.com.epermatozoideguerreiro.cdc.book;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.Future;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import br.com.epermatozoideguerreiro.cdc.author.Author;
import br.com.epermatozoideguerreiro.cdc.category.Category;

@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String title;

    @NotBlank
    @Size(max=500)
    private String description;

    private String summary;

    @NotNull
    @Min(20)
    private BigDecimal price;
    
    @NotNull
    @Min(100)
    private int pages;
    
    @NotBlank
    private String isbn;
    
    @Future
    @NotNull
    private LocalDate publicationDate;

    @ManyToOne
    private @NotNull @Valid Author author;

    @ManyToOne
    private @NotNull @Valid Category category;

    

    public Book() {
    }

    public Book(@NotBlank String title, @NotBlank @Size(max = 500) String description, String summary,
            @NotBlank @Min(20) BigDecimal price, @NotBlank @Min(100) int pages, String isbn,
            @Future LocalDate publicationDate, @NotBlank Author author, @NotBlank Category category) {
        this.title = title;
        this.description = description;
        this.summary = summary;
        this.price = price;
        this.pages = pages;
        this.isbn = isbn;
        this.publicationDate = publicationDate;
        this.author = author;
        this.category = category;
    }
    
    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getSummary() {
        return summary;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public int getPages() {
        return pages;
    }

    public String getIsbn() {
        return isbn;
    }

    public LocalDate getPublicationDate() {
        return publicationDate;
    }

    public Author getAuthor() {
        return author;
    }

    public Category getCategory() {
        return category;
    }

    @Override
    public String toString() {
        return "Book [id=" + id + ", title=" + title + ", description=" + description + ", summary=" + summary
                + ", price=" + price + ", pages=" + pages + ", isbn=" + isbn + ", publicationDate=" + publicationDate
                + ", author=" + author + ", category=" + category + "]";
    }   
 
}
