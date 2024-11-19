package unipar.integrador.mywallet.application.dto.transacao;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.util.UUID;

public record TransacaoDTO(

        @NotNull(message = "Tipo de Transação é obrigatório.")
        UUID tipoTransacaoId,

        @NotNull(message = "Categoria é obrigatória.")
        UUID categoriaId,

        @NotNull(message = "Subcategoria é obrigatória.")
        UUID subcategoriaId,

        @NotNull(message = "Método de Pagamento é obrigatório.")
        UUID metodoPagamentoId,

        @NotNull(message = "Conta Bancária é obrigatória.")
        UUID contaBancariaId,

        @Positive(message = "Valor deve ser positivo.")
        double valor,

        @Size(max = 200, message = "Descrição deve ter no máximo 200 caracteres.")
        String descricao
) {}
