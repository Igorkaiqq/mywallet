package unipar.integrador.mywallet.application.dto.transacao;

import java.util.UUID;

public record TransacaoUsuarioDTO(UUID id, java.time.LocalDate data, String nomeTransacao, String nomeCategoria, String nomeSubcategoria, String descricao, Double valor) {
}
