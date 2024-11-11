package unipar.integrador.mywallet.application.dto.contaBancaria;

import jakarta.validation.constraints.NotNull;


public record CadastroContaBancariaDTO(

        @NotNull(message = "Nome é obrigatório.")
        String nome,
        @NotNull(message = "Banco é obrigatório.")
        double saldo

) {
}
