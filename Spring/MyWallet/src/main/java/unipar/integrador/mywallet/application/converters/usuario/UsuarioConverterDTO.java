package unipar.integrador.mywallet.application.converters.usuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import unipar.integrador.mywallet.application.dto.usuario.CadastroUsuarioDTO;
import unipar.integrador.mywallet.application.entities.Role;
import unipar.integrador.mywallet.application.entities.UsuarioEntity;
import unipar.integrador.mywallet.application.enums.GeneroEnum;
import unipar.integrador.mywallet.application.enums.StatusRegistroEnum;
import unipar.integrador.mywallet.infrastructure.repository.RoleRepository;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Set;

@Component
public class UsuarioConverterDTO {

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UsuarioConverterDTO(RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public UsuarioEntity toEntity(CadastroUsuarioDTO dto) {
        var roleBasic = roleRepository.findByNome(Role.Values.ADMIN.name());

        UsuarioEntity usuarioEntity = new UsuarioEntity();
        usuarioEntity.setNome(dto.nome());
        usuarioEntity.setUsername(dto.username());
        usuarioEntity.setEmail(dto.email());
        usuarioEntity.setSenha(passwordEncoder.encode(dto.senha()));
        usuarioEntity.setTelefone(dto.telefone());
        usuarioEntity.setCpf(dto.cpf());
        usuarioEntity.setGenero(GeneroEnum.fromString(dto.genero().toString()));
        usuarioEntity.setDataNascimento(LocalDate.parse(dto.dataNascimento(), formatter));
        usuarioEntity.setDataCadastro(LocalDate.now());
        usuarioEntity.setPerguntaSecreta(dto.perguntaSecreta());
        usuarioEntity.setRespostaSecreta(dto.respostaSecreta());
        usuarioEntity.setStatusRegistro(StatusRegistroEnum.ATIVO);
        usuarioEntity.setRoles(Set.of(roleBasic));

        return usuarioEntity;
    }

    public CadastroUsuarioDTO toDTO(UsuarioEntity entity) {
        return new CadastroUsuarioDTO(
                entity.getNome(),
                entity.getUsername(),
                entity.getEmail(),
                entity.getSenha(),
                entity.getTelefone(),
                entity.getCpf(),
                entity.getGenero(),
                entity.getDataNascimento().toString(),
                entity.getPerguntaSecreta(),
                entity.getRespostaSecreta()
        );
    }
}
