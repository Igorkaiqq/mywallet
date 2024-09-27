package unipar.integrador.mywallet.application.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.server.ResponseStatusException;
import unipar.integrador.mywallet.application.converters.usuario.UsuarioConverterDTO;
import unipar.integrador.mywallet.application.dto.categoriaUsuario.CategoriaUsuarioDTO;
import unipar.integrador.mywallet.application.dto.subcategoriaUsuario.SubcategoriaUsuarioDTO;
import unipar.integrador.mywallet.application.dto.usuario.AtualizarUsuarioDTO;
import unipar.integrador.mywallet.application.dto.usuario.CadastroUsuarioDTO;
import unipar.integrador.mywallet.application.entities.*;
import unipar.integrador.mywallet.application.enums.GeneroEnum;
import unipar.integrador.mywallet.application.enums.StatusRegistroEnum;
import unipar.integrador.mywallet.application.exception.CamposDuplicadosUsuarioException;
import unipar.integrador.mywallet.application.exception.ExceptionUtils;
import unipar.integrador.mywallet.application.exception.GlobalExceptionHandler;
import unipar.integrador.mywallet.application.exception.UsuarioNaoEncontradoException;
import unipar.integrador.mywallet.application.interfaces.IUsuario;
import unipar.integrador.mywallet.application.services.subservice.CategoriaSubcategoriaService;
import unipar.integrador.mywallet.infrastructure.repository.UsuarioRepository;
import unipar.integrador.mywallet.application.dto.usuario.LoginDTO;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UsuarioService implements IUsuario {

    @Autowired
    private  UsuarioRepository usuarioRepository;

    @Autowired
    private CategoriaPadraoService categoriaPadraoService;

    @Autowired
    private CategoriaSubcategoriaService categoriaSubcategoriaService;


    @Override
    public UsuarioEntity save(CadastroUsuarioDTO dto) {

        try {
            UsuarioEntity usuarioEntity = UsuarioConverterDTO.toEntity(dto);
            UsuarioEntity usuarioSalvo = usuarioRepository.save(usuarioEntity);

            List<CategoriaPadraoEntity> categoriasPadrao = categoriaPadraoService.findAllCategoriasAtivasEntities();
            categoriaSubcategoriaService.associarCategoriasComUsuario(usuarioSalvo, categoriasPadrao);

            return usuarioSalvo;
        } catch (DataIntegrityViolationException e) {
            String camposDuplicados = ExceptionUtils.extractDuplicatedFields(e.getMessage());
            throw new CamposDuplicadosUsuarioException(camposDuplicados);
        }
    }

    @Override
    public Optional<UsuarioEntity> findById (UUID id){
        return usuarioRepository.findById(id);
    }

    @Override
    public List<UsuarioEntity> findAll () {
        return usuarioRepository.findAll();
    }

    @Override
    public UsuarioEntity update (UUID id, AtualizarUsuarioDTO atualizarUsuarioDTO){

        UsuarioEntity usuarioExistente = usuarioRepository.findById(id)
                .orElseThrow(() -> new UsuarioNaoEncontradoException("Usuário não encontrado"));

        usuarioExistente.setNome(atualizarUsuarioDTO.nome());
        usuarioExistente.setEmail(atualizarUsuarioDTO.email());
        usuarioExistente.setSenha(atualizarUsuarioDTO.senha());
        usuarioExistente.setTelefone(atualizarUsuarioDTO.telefone());

        return usuarioRepository.save(usuarioExistente);
    }

    @Override
    public void deleteById (UUID id){
        UsuarioEntity usuarioEntity = usuarioRepository.findById(id)
                .orElseThrow(() -> new UsuarioNaoEncontradoException("Usuário com ID " + id + " não encontrado"));
        usuarioEntity.setStatusRegistro(StatusRegistroEnum.DELETADO);
        usuarioRepository.save(usuarioEntity);
    }

    @Override
    public UsuarioEntity realizarLogin(LoginDTO loginDto) {

        UsuarioEntity usuario = usuarioRepository.findByEmailOrUsername(loginDto.emailOuUsername(), loginDto.emailOuUsername())
                .orElseThrow(() -> new UsuarioNaoEncontradoException("Usuário não encontrado"));

        if (!usuario.getSenha().equals(loginDto.senha())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Senha inválida");
        }

        return usuario;
    }


}
