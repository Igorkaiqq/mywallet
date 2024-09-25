package unipar.integrador.mywallet.application.validators.constraint;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import unipar.integrador.mywallet.application.validators.annotation.ValidCPF;

public class CPFValidator implements ConstraintValidator<ValidCPF, String> {

    @Override
    public boolean isValid(String cpf, ConstraintValidatorContext context) {
        if (cpf == null || cpf.isEmpty()) {
            return false;
        }

        cpf = cpf.replace(".", "").replace("-", "");

        if (cpf.length() != 11 || cpf.matches("(\\d)\\1{10}")) {
            return false;
        }

        try {
            int soma = 0;
            for (int i = 0; i < 9; i++) {
                soma += (cpf.charAt(i) - '0') * (10 - i);
            }

            int digito1 = 11 - (soma % 11);

            digito1 = (digito1 >= 10) ? 0 : digito1;

            soma = 0;

            for (int i = 0; i < 10; i++) {
                soma += (cpf.charAt(i) - '0') * (11 - i);
            }

            int digito2 = 11 - (soma % 11);

            digito2 = (digito2 >= 10) ? 0 : digito2;

            return cpf.charAt(9) - '0' == digito1 && cpf.charAt(10) - '0' == digito2;

        } catch (NumberFormatException e) {
            return false;
        }
    }
}
