package unipar.integrador.mywallet.application.validators.annotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import unipar.integrador.mywallet.application.validators.constraint.TelefoneValidator;

import java.lang.annotation.*;

@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {TelefoneValidator.class})
@Documented
public @interface ValidTelefone {

    String message() default "O telefone está inválido, tem que estar no formato (xx) xxxx-xxxx ou (xx) xxxxx-xxxx";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

}
