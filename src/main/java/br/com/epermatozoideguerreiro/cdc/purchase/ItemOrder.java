package br.com.epermatozoideguerreiro.cdc.purchase;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import br.com.epermatozoideguerreiro.cdc.book.Book;

@Entity
public class ItemOrder {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;    

    @NotNull
    @ManyToOne
    private Book book;
    
    @NotNull
    @Positive    
    private Integer quantity;

    public Integer getQuantity() {
        return quantity;
    }
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
    public Long getId() {
        return id;
    }    

    public Book getBook() {
        return book;
    }
    public void setBook(Book book) {
        this.book = book;
    }
    @Override
    public String toString() {
        return "ItemOrder [id=" + id + ", book=" + book + ", quantity=" + quantity + "]";
    }

    public ItemOrder() {
    }
    public ItemOrder(@NotNull Book book, @NotNull @Positive Integer quantity) {
        this.book = book;
        this.quantity = quantity;
    }    

    

}
