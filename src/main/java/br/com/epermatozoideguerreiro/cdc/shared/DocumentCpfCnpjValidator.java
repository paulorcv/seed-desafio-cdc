package br.com.epermatozoideguerreiro.cdc.shared;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import br.com.epermatozoideguerreiro.cdc.purchase.NewPurchaseRequest;

public class DocumentCpfCnpjValidator implements Validator{

    @Override
    public boolean supports(Class<?> clazz) {
        return NewPurchaseRequest.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
         if(errors.hasErrors()) return;

         NewPurchaseRequest request = (NewPurchaseRequest) target;

         if(!request.isValidDocument()) {
            errors.rejectValue("document", null, "documento precisa de ser CPF ou CNPJ");
         }
    }

}
