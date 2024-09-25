package unipar.integrador.mywallet.application.converters.usuario;

import unipar.integrador.mywallet.application.dto.usuario.CadastroUsuarioDTO;
import unipar.integrador.mywallet.application.entities.UsuarioEntity;
import unipar.integrador.mywallet.application.enums.GeneroEnum;
import unipar.integrador.mywallet.application.enums.StatusRegistroEnum;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class UsuarioConverterDTO {

        private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        public static UsuarioEntity toEntity(CadastroUsuarioDTO dto) {
            UsuarioEntity usuarioEntity = new UsuarioEntity();
            usuarioEntity.setNome(dto.nome());
            usuarioEntity.setUsername(dto.username());
            usuarioEntity.setEmail(dto.email());
            usuarioEntity.setSenha(dto.senha());
            usuarioEntity.setTelefone(dto.telefone());
            usuarioEntity.setCpf(dto.cpf());
            usuarioEntity.setGenero(GeneroEnum.fromString(dto.genero()));
            usuarioEntity.setDataNascimento(LocalDate.parse(dto.dataNascimento(), formatter));
            usuarioEntity.setDataCadastro(LocalDate.now());
            usuarioEntity.setPerguntaSecreta(dto.perguntaSecreta());
            usuarioEntity.setRespostaSecreta(dto.respostaSecreta());
            usuarioEntity.setStatusRegistro(StatusRegistroEnum.ATIVO);

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
