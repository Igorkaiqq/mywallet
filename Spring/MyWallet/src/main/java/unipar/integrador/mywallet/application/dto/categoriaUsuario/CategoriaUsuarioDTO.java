package unipar.integrador.mywallet.application.dto.categoriaUsuario;

import unipar.integrador.mywallet.application.enums.StatusRegistroEnum;

import java.util.UUID;

public record CategoriaUsuarioDTO (UUID id, UUID usuarioId, UUID tipoTransacao, UUID categoriaPadrao, String nome, StatusRegistroEnum statusRegistro) {
}
