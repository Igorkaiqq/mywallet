package unipar.integrador.mywallet.application.dto.contaBancaria;

import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record CadastroContaBancaria(

        @NotNull(message = "Usuário é obrigatório.")
        UUID UsuarioId,
        @NotNull(message = "Nome é obrigatório.")
        String nome,
        @NotNull(message = "Banco é obrigatório.")
        double saldo

) {
}
