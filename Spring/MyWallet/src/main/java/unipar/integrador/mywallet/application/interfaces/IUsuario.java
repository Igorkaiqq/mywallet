package unipar.integrador.mywallet.application.interfaces;

import unipar.integrador.mywallet.application.dto.usuario.CadastroUsuarioDto;
import unipar.integrador.mywallet.application.entities.UsuarioEntity;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


public interface IUsuario {

    UsuarioEntity save(CadastroUsuarioDto dto);
    Optional<UsuarioEntity> findById(UUID id);
    List<UsuarioEntity> findAll();
    UsuarioEntity update(UsuarioEntity usuarioEntity);
    void deleteById(UUID id);

}
