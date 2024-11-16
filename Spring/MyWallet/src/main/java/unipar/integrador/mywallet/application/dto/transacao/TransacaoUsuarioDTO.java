package unipar.integrador.mywallet.application.dto.transacao;

import java.time.LocalDateTime;
import java.util.UUID;

public record TransacaoUsuarioDTO(
        UUID id,
        LocalDateTime data,
        String nomeTransacao,
        String nomeCategoria,
        String nomeSubcategoria,
        String descricao,
        Double valor) {
}
