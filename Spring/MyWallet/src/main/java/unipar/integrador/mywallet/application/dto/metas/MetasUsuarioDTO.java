package unipar.integrador.mywallet.application.dto.metas;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.util.UUID;

public record MetasUsuarioDTO(

        @NotNull(message = "Categoria é obrigatória!")
        UUID categoriaId,

        @Positive(message = "Valor não pode ser negativo!")
        double valor

) {}
