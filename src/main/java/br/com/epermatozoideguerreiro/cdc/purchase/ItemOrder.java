package br.com.epermatozoideguerreiro.cdc.purchase;

import java.math.BigDecimal;

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

    @NotNull
    @Positive
    private BigDecimal price;    

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

    public ItemOrder() {
    }
    
    public ItemOrder(@NotNull Book book, @NotNull @Positive Integer quantity, @NotNull @Positive BigDecimal price) {
        this.book = book;
        this.quantity = quantity;
        this.price = price;
    }
    public BigDecimal getPrice() {
        return price;
    }
    public void setPrice(BigDecimal price) {
        this.price = price;
    }
    @Override
    public String toString() {
        return "ItemOrder [id=" + id + ", book=" + book + ", quantity=" + quantity + ", price=" + price + "]";
    }    

    

}
