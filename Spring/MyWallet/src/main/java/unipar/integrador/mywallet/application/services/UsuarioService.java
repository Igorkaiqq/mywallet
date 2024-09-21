package unipar.integrador.mywallet.application.services;

import org.springframework.stereotype.Service;
import unipar.integrador.mywallet.application.dto.CadastroUsuarioDto;
import unipar.integrador.mywallet.application.entities.UsuarioEntity;
import unipar.integrador.mywallet.application.enums.GeneroEnum;
import unipar.integrador.mywallet.application.enums.StatusRegistroEnum;
import unipar.integrador.mywallet.application.interfaces.IUsuario;
import unipar.integrador.mywallet.infrastructure.repository.UsuarioRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UsuarioService implements IUsuario {

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public UsuarioEntity save(CadastroUsuarioDto dto) {
        UsuarioEntity usuarioEntity = new UsuarioEntity();
        usuarioEntity.setNome(dto.nome());
        usuarioEntity.setUsername(dto.username());
        usuarioEntity.setEmail(dto.email());
        usuarioEntity.setSenha(dto.senha());
        usuarioEntity.setTelefone(dto.telefone());
        usuarioEntity.setCpf(dto.cpf());
        usuarioEntity.setGenero(GeneroEnum.valueOf(dto.genero()));
        usuarioEntity.setDataNascimento(LocalDate.parse(dto.dataNascimento()));
        usuarioEntity.setDataCadastro(LocalDateTime.now());
        usuarioEntity.setPerguntaSecreta(dto.perguntaSecreta());
        usuarioEntity.setRespostaSecreta(dto.respostaSecreta());
        usuarioEntity.setStatusRegistro(StatusRegistroEnum.ATIVO);
        return usuarioRepository.save(usuarioEntity);
    }

    @Override
    public Optional<UsuarioEntity> findById(UUID id) {
        return usuarioRepository.findById(id);
    }

    @Override
    public List<UsuarioEntity> findAll() {
        return usuarioRepository.findAll();
    }

    @Override
    public UsuarioEntity update(UsuarioEntity usuarioEntity) {
        return usuarioRepository.save(usuarioEntity);
    }

    @Override
    public void deleteById(UUID id) {
        UsuarioEntity usuarioEntity = usuarioRepository.findById(id).orElseThrow();
        usuarioEntity.setStatusRegistro(StatusRegistroEnum.DELETADO);
        usuarioRepository.save(usuarioEntity);
    }
}
