package br.com.epermatozoideguerreiro.cdc.shared;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;



@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ExistsByFieldValidator.class)
@Documented
public @interface ExistsByField {
    
    String message() default "Não existe a entidade" ;

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };

    String fieldname();

    Class<?> domainClass();

}
