package unipar.integrador.mywallet.application.dto.transacao;

import jakarta.validation.constraints.*;
import java.util.UUID;

public record TransacaoDTO(
        @NotNull(message = "Usuário é obrigatório.")
        UUID usuarioId,

        @NotNull(message = "Tipo de Transação é obrigatório.")
        UUID tipoTransacaoId,

        @NotNull(message = "Categoria é obrigatória.")
        UUID categoriaId,

        @NotNull(message = "Subcategoria é obrigatória.")
        UUID subcategoriaId,

        @NotNull(message = "Método de Pagamento é obrigatório.")
        UUID metodoPagamentoId,

        @Positive(message = "Valor deve ser positivo.")
        double valor,

        @Size(max = 200, message = "Descrição deve ter no máximo 200 caracteres.")
        String descricao
) {}
