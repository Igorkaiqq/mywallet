package unipar.integrador.mywallet.application.interfaces;

import unipar.integrador.mywallet.application.dto.usuario.AtualizarUsuarioDTO;
import unipar.integrador.mywallet.application.dto.usuario.CadastroUsuarioDTO;
import unipar.integrador.mywallet.application.dto.usuario.LoginDTO;
import unipar.integrador.mywallet.application.entities.UsuarioEntity;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


public interface IUsuario {

    UsuarioEntity save(CadastroUsuarioDTO dto);
    Optional<UsuarioEntity> findById(UUID id);
    List<UsuarioEntity> findAll();
    UsuarioEntity update(UUID id, AtualizarUsuarioDTO dto);
    void deleteById(UUID id);
    UsuarioEntity realizarLogin(LoginDTO loginDto);

}
