package unipar.integrador.mywallet.application.interfaces;

import unipar.integrador.mywallet.application.dto.metas.MetasUsuarioDTO;
import unipar.integrador.mywallet.application.entities.MetasUsuarioEntity;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IMetasUsuario {

    MetasUsuarioEntity save(MetasUsuarioDTO dto);
    Optional<MetasUsuarioEntity> findById(UUID id);
    List<MetasUsuarioEntity> findAll();
    MetasUsuarioEntity update(UUID id, MetasUsuarioDTO dto);
}
