package br.com.epermatozoideguerreiro.cdc.shared;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

public class ExistsByFieldValidator implements ConstraintValidator<ExistsByField, Object>{

    private String domainAttribute;
    private Class<?> klass;

    @PersistenceContext
    @Autowired
    private EntityManager manager;


    @Override
    public void initialize(ExistsByField params) {
        domainAttribute = params.fieldname();
        klass = params.domainClass();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        
        if(value == null) return true;
        
        Query query = manager.createQuery("select 1 from " + klass.getName() + " where " + domainAttribute + "=:value");
         query.setParameter("value", value);
         List<?> list = query.getResultList();
         Assert.state(list.size() <= 1, "Foi encontrado mais de um " + klass + " com o atributo "  + domainAttribute);
        
        return !list.isEmpty();
    }

    
}
