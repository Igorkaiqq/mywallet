package unipar.integrador.mywallet.application.dto.dashboard;

import java.time.LocalDateTime;

public record MaioresTransacoesDTO(LocalDateTime data, String descricao, String nomeCategoria, String nomeSubcategoria, Double valor) {
}
