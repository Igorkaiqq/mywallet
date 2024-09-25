package unipar.integrador.mywallet.application.validators.annotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import unipar.integrador.mywallet.application.validators.constraint.DataNascimentoValidador;

import java.lang.annotation.*;

@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {DataNascimentoValidador.class})
@Documented
public @interface ValidDataNascimento {
    String message() default "Data de nascimento deve estar no formato brasileiro (dd/mm/aaaa)";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
