package br.com.epermatozoideguerreiro.cdc.book;

import java.math.BigDecimal;
import java.time.LocalDate;

import br.com.epermatozoideguerreiro.cdc.author.AuthorResponse;
import br.com.epermatozoideguerreiro.cdc.category.CategoryResponse;

public class BookResponse {

    private Long id;
    private String title;
    private String description;
    private String summary;
    private BigDecimal price;
    private int pages;
    private String isbn;
    private LocalDate publicationDate;
    private AuthorResponse author;
    private CategoryResponse category;

    public BookResponse(Book book) {
        this.title = book.getTitle();
        this.description = book.getDescription();
        this.summary = book.getSummary();
        this.price = book.getPrice();
        this.pages = book.getPages();
        this.isbn = book.getIsbn();
        this.publicationDate = book.getPublicationDate();
        this.author = new AuthorResponse(book.getAuthor());
        this.category = new CategoryResponse(book.getCategory());
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

    public AuthorResponse getAuthor() {
        return author;
    }

    public CategoryResponse getCategory() {
        return category;
    }

}
