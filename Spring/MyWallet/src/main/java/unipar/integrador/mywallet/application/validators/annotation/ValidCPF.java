package unipar.integrador.mywallet.application.validators.annotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import unipar.integrador.mywallet.application.validators.constraint.CPFValidator;

import java.lang.annotation.*;

@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {CPFValidator.class})
@Documented
public @interface ValidCPF {
    String message() default "O CPF está inválido, tem que estar no formato xxx.xxx.xxx-xx e ser existente";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
