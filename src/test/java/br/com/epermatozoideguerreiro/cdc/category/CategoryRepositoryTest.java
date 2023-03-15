package br.com.epermatozoideguerreiro.cdc.category;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class CategoryRepositoryTest {

    @Mock
    CategoryRepository repository;

    @Test
    public void saveCategoryTest(){

        Category category = new Category("teste");

        Mockito.when(repository.save(Mockito.any(Category.class)))
                .thenReturn(category);

        Category savedCategory = repository.save(category);
        Assertions.assertEquals(category.getName(), savedCategory.getName());
    }


    @Test
    public void findByNameTest(){

        Category category = new Category("teste");

        Mockito.when(repository.findByName(Mockito.anyString()))
                .thenReturn(Optional.of(category));

        Optional<Category> returnedCategory = repository.findByName(category.getName());
        Assertions.assertEquals(category.getName(), returnedCategory.get().getName());
    }
}
