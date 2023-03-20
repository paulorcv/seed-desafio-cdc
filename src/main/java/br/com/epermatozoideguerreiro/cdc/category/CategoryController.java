package br.com.epermatozoideguerreiro.cdc.category;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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
