package br.com.epermatozoideguerreiro.cdc.category;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class CategoryAlreadyExistsValidatorTest {

    @Autowired
    private CategoryAlreadyExistsValidator validator;

    @MockBean
    private CategoryRepository repository;

    Errors errors;

    Optional<Category> category;

    @BeforeEach
    public void setup() {
        category = Optional.of(new Category("teste"));
    }
    @Test
    public void duplicateCategory() throws Exception {

        NewCategoryRequest newCategoryRequest = new NewCategoryRequest("teste");
        Mockito.when(repository.findByName(Mockito.anyString())).thenReturn(category);
        errors = new BeanPropertyBindingResult(newCategoryRequest, "");

        validator.validate(newCategoryRequest, errors);
        assertTrue(errors.hasErrors());

    }

    @Test
    public void successCategory() throws Exception {

        NewCategoryRequest newCategoryRequest = new NewCategoryRequest("teste");
        errors = new BeanPropertyBindingResult(newCategoryRequest, "");

        validator.validate(newCategoryRequest, errors);
        assertFalse(errors.hasErrors());

    }


}
