package br.com.epermatozoideguerreiro.cdc.purchase;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import br.com.epermatozoideguerreiro.cdc.book.Book;
import br.com.epermatozoideguerreiro.cdc.book.BookRepository;

public class ItemOrderRequest {

    @NotNull
    private Long idBook;
    
    @NotNull
    @Positive    
    private Integer quantity;

    public Long getIdBook() {
        return idBook;
    }
    public void setIdBook(Long idBook) {
        this.idBook = idBook;
    }
    public Integer getQuantity() {
        return quantity;
    }
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
   

    public ItemOrderRequest(@NotNull Long idBook, @NotNull @Positive Integer quantity) {
        this.idBook = idBook;
        this.quantity = quantity;
    }
    public ItemOrder toModel(BookRepository bookRepository) {
        Book book = bookRepository.findById(idBook).get();
        return new ItemOrder(book, quantity);
    }
    
}
