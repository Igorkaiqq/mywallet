package unipar.integrador.mywallet.application.interfaces;

import unipar.integrador.mywallet.application.dto.subcategoriaUsuario.SubcategoriaUsuarioDTO;
import unipar.integrador.mywallet.application.entities.SubcategoriaUsuarioEntity;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ISubcategoriaUsuario {

    SubcategoriaUsuarioEntity save(SubcategoriaUsuarioDTO dto);
    Optional<SubcategoriaUsuarioEntity> findById(UUID id);
    List<SubcategoriaUsuarioEntity> findAll();

    SubcategoriaUsuarioEntity update(SubcategoriaUsuarioDTO subcategoriaUsuario);

    void deleteById(UUID id);

}
