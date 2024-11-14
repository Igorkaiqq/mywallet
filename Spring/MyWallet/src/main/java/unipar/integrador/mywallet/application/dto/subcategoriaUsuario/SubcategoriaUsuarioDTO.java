package unipar.integrador.mywallet.application.dto.subcategoriaUsuario;

import unipar.integrador.mywallet.application.enums.StatusRegistroEnum;

import java.util.UUID;

public record SubcategoriaUsuarioDTO(UUID id, UUID usuarioId, UUID categoriaUsuarioId, UUID subcategoriaPadraoId, String nome, StatusRegistroEnum statusRegistro) {
}
