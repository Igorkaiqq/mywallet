package unipar.integrador.mywallet.application.interfaces;

import unipar.integrador.mywallet.application.dto.categoriaUsuario.CategoriaUsuarioDTO;
import unipar.integrador.mywallet.application.entities.CategoriaUsuarioEntity;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ICategoriaUsuario {

    CategoriaUsuarioEntity save(CategoriaUsuarioDTO dto);
    Optional<CategoriaUsuarioEntity> findById(UUID id);
    List<CategoriaUsuarioEntity> findAll();
    CategoriaUsuarioDTO update(CategoriaUsuarioDTO dto);
    void deleteById(UUID id);
    List<CategoriaUsuarioDTO> findAllCategoriasUsuariosAtivas();

    CategoriaUsuarioEntity savePersonalizada(CategoriaUsuarioDTO dto);
}
