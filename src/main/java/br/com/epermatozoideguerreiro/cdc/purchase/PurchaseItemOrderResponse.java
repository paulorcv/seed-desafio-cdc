package br.com.epermatozoideguerreiro.cdc.purchase;

import java.math.BigDecimal;

public class PurchaseItemOrderResponse {

    private Long id;

    private PurchaseBook book;

    private Integer quantity;

    private BigDecimal price;

    public PurchaseItemOrderResponse() {
    }

    public PurchaseItemOrderResponse(ItemOrder item) {

        this.id = item.getId();
        this.book = new PurchaseBook(item.getBook());
        this.quantity = item.getQuantity();
        this.price = item.getPrice();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PurchaseBook getBook() {
        return book;
    }

    public void setBook(PurchaseBook book) {
        this.book = book;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }


    
}
