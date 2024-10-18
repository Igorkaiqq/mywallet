package unipar.integrador.mywallet.application.dto.caixa;

import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record CadastroCaixa(

        @NotNull
        UUID UsuarioId,

        @NotNull
        double saldo

) {
}
