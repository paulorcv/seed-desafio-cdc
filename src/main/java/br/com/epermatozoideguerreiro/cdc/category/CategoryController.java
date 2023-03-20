package br.com.epermatozoideguerreiro.cdc.category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@Validated
public class CategoryController {

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    CategoryAlreadyExistsValidator categoryAlreadyExistsValidator;

    @PostMapping(value = "/api/category")
    @Transactional
    public void create(@Valid @RequestBody NewCategoryRequest request) {
        categoryRepository.save(request.toModel());
    }

}
