package unipar.integrador.mywallet.application.validators.constraint;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import unipar.integrador.mywallet.application.validators.annotation.ValidDataNascimento;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DataNascimentoValidador implements ConstraintValidator<ValidDataNascimento, String> {

    private static final String DATE_PATTERN = "dd/MM/yyyy";

    @Override
    public void initialize(ValidDataNascimento constraintAnnotation) {
    }

    @Override
    public boolean isValid(String dataNascimento, ConstraintValidatorContext context) {

        if (dataNascimento == null || dataNascimento.isEmpty()) {
            return false;
        }

        try {

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_PATTERN);
            LocalDate.parse(dataNascimento, formatter);
            return true;

        } catch (DateTimeParseException e) {
            return false;
        }
    }
}
