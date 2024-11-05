package unipar.integrador.mywallet.application.converters.usuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import unipar.integrador.mywallet.application.dto.usuario.CadastroUsuarioDTO;
import unipar.integrador.mywallet.application.entities.Role;
import unipar.integrador.mywallet.application.entities.UsuarioEntity;
import unipar.integrador.mywallet.application.enums.GeneroEnum;
import unipar.integrador.mywallet.application.enums.StatusRegistroEnum;
import unipar.integrador.mywallet.infrastructure.repository.RoleRepository;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Set;

public class UsuarioConverterDTO {

        private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        private static RoleRepository roleRepository;

        private static PasswordEncoder passwordEncoder;

    public UsuarioConverterDTO(RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public static UsuarioEntity toEntity(CadastroUsuarioDTO dto) {
            var roleBasic = roleRepository.findByNome(Role.Values.ADMIN.name());
            UsuarioEntity usuarioEntity = new UsuarioEntity();
            usuarioEntity.setNome(dto.nome());
            usuarioEntity.setUsername(dto.username());
            usuarioEntity.setEmail(dto.email());
            usuarioEntity.setSenha(passwordEncoder.encode(dto.senha()));
            usuarioEntity.setTelefone(dto.telefone());
            usuarioEntity.setCpf(dto.cpf());
            usuarioEntity.setGenero(GeneroEnum.fromString(dto.genero()));
            usuarioEntity.setDataNascimento(LocalDate.parse(dto.dataNascimento(), formatter));
            usuarioEntity.setDataCadastro(LocalDate.now());
            usuarioEntity.setPerguntaSecreta(dto.perguntaSecreta());
            usuarioEntity.setRespostaSecreta(dto.respostaSecreta());
            usuarioEntity.setStatusRegistro(StatusRegistroEnum.ATIVO);
            usuarioEntity.setRoles(Set.of(roleBasic));

            return usuarioEntity;
        }

        public static CadastroUsuarioDTO toDTO(UsuarioEntity entity) {

            CadastroUsuarioDTO usuarioDTO = new CadastroUsuarioDTO(
                    entity.getNome(),
                    entity.getUsername(),
                    entity.getEmail(),
                    entity.getSenha(),
                    entity.getTelefone(),
                    entity.getCpf(),
                    entity.getGenero().toString(),
                    entity.getDataNascimento().toString(),
                    entity.getPerguntaSecreta(),
                    entity.getRespostaSecreta()
            );

            return usuarioDTO;
        }

}
