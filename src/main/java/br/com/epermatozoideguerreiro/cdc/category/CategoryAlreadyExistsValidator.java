package br.com.epermatozoideguerreiro.cdc.category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Optional;

@Component
public class CategoryAlreadyExistsValidator implements Validator {

    @Autowired
    CategoryRepository categoryRepository;

    @Override
    public boolean supports(Class<?> clazz) {
        return NewCategoryRequest.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        if (errors.hasErrors()) return;

        NewCategoryRequest request = (NewCategoryRequest) target;
        Optional<Category> category = categoryRepository.findByName(request.getName());

        if (category.isPresent()) {
            errors.rejectValue("name", null, "Já existe categoria com este nome:" + request.getName());
        }
    }
}
