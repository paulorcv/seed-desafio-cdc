package br.com.epermatozoideguerreiro.cdc.book;

import java.math.BigDecimal;
import java.time.LocalDate;

public class BookResponse {

    private Long id;
    private String title;
    private String description;
    private String summary;
    private BigDecimal price;
    private int pages;
    private String isbn;
    private LocalDate publicationDate;
    private BookAuthorResponse author;
    private BookCategoryResponse category;

    public BookResponse(Book book) {
        this.id = book.getId();
        this.title = book.getTitle();
        this.description = book.getDescription();
        this.summary = book.getSummary();
        this.price = book.getPrice();
        this.pages = book.getPages();
        this.isbn = book.getIsbn();
        this.publicationDate = book.getPublicationDate();
        this.author = new BookAuthorResponse(book.getAuthor());
        this.category = new BookCategoryResponse(book.getCategory());
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

    public BookAuthorResponse getAuthor() {
        return author;
    }

    public BookCategoryResponse getCategory() {
        return category;
    }

}
