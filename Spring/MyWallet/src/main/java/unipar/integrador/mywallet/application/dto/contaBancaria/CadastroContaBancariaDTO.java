package unipar.integrador.mywallet.application.dto.contaBancaria;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;


public record CadastroContaBancariaDTO(

        @NotBlank(message = "Nome do Banco é obrigatório.")
        String nome,
        @Positive(message = "Banco deve ser positivo e maior que zero.")
        double saldo

) {
}
