package unipar.integrador.mywallet.application.dto.transacao;

import java.util.UUID;

public record TransacaoDTO(UUID usuarioId, UUID tipoTransacaoId, UUID categoriaId, UUID subcategoriaId, UUID metodoPagamentoID, double valor, String descricao) {
}
