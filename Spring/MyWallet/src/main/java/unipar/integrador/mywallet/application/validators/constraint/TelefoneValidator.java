package unipar.integrador.mywallet.application.validators.constraint;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import unipar.integrador.mywallet.application.validators.annotation.ValidTelefone;

import java.util.regex.Pattern;

public class TelefoneValidator implements ConstraintValidator<ValidTelefone, String> {

    private final String TELEFONE_PATTERN = "\\(\\d{2}\\) \\d{4,5}-\\d{4}";

    @Override
    public boolean isValid(String telefone, ConstraintValidatorContext context) {
        if (telefone == null || telefone.isEmpty()) {
            return false;
        }
        boolean isValid = Pattern.matches(TELEFONE_PATTERN, telefone);
        if (!isValid) {
            System.out.println("Telefone inválido");
        }
        return isValid;
    }
}
