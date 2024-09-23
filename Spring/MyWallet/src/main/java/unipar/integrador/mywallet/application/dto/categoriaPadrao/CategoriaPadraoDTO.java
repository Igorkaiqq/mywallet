package unipar.integrador.mywallet.application.dto.categoriaPadrao;

import unipar.integrador.mywallet.application.enums.StatusRegistroEnum;

import java.util.UUID;

public record CategoriaPadraoDTO(UUID id, UUID transacaoId , String nome, StatusRegistroEnum statusRegistro) {
}
