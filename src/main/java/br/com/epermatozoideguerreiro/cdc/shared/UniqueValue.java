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
@Constraint(validatedBy = UniqueValueValidator.class)
@Documented
public @interface UniqueValue {
    
    String message() default "Valor único já existente" ;

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };

    String fieldname();

    Class<?> domainClass();

}
