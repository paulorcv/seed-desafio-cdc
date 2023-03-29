package br.com.epermatozoideguerreiro.cdc.purchase;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import br.com.epermatozoideguerreiro.cdc.book.Book;
import br.com.epermatozoideguerreiro.cdc.book.BookRepository;

@Component
class ItemsOrderValidator implements Validator {

    @Autowired
    BookRepository bookRepository;

    @Override
    public boolean supports(Class<?> clazz) {
        return NewPurchaseRequest.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

        if (errors.hasErrors())
            return;
        NewPurchaseRequest request = (NewPurchaseRequest) target;

        List<ItemOrderRequest> itemsOrder = request.getItemsOrder();
        BigDecimal totalItems = BigDecimal.ZERO;

        if (itemsOrder.isEmpty()) {
            errors.rejectValue("itemsOrder", null, "Items não podem estar vazios na compra");
        } else {

            for (ItemOrderRequest item : itemsOrder) {
                Optional<Book> book = bookRepository.findById(item.getIdBook());

                if (book.isEmpty()) {
                    errors.rejectValue("itemsOrder", null, "Não existe livro com o id: " + item.getIdBook());
                    return;
                } else {

                    BigDecimal price = book.get().getPrice();
                    totalItems = totalItems.add(price.multiply(BigDecimal.valueOf(item.getQuantity())));
                }
            }

            BigDecimal total = request.getTotal();
            if (total.compareTo(totalItems) != 0) {
                errors.rejectValue("itemsOrder", null,
                        "Total enviado: " + total + " é diferente do total calculado: " + totalItems);
            }
        }

    }
}
