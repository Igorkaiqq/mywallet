package unipar.integrador.mywallet.application.dto.usuario;

import jakarta.validation.constraints.*;
import lombok.Getter;
import unipar.integrador.mywallet.application.validators.annotation.ValidCPF;
import unipar.integrador.mywallet.application.validators.annotation.ValidDataNascimento;
import unipar.integrador.mywallet.application.validators.annotation.ValidTelefone;

public record CadastroUsuarioDTO(
        @NotBlank(message = "O nome não pode estar em branco")
        @Size(max = 70, message = "O nome não pode ter mais de 70 caracteres")
        String nome,

        @NotBlank(message = "O username não pode estar em branco")
        @Size(max = 70, message = "O username não pode ter mais de 70 caracteres")
        String username,

        @NotBlank(message = "O e-mail não pode estar em branco")
        @Email(message = "E-mail inválido")
        String email,

        @NotBlank(message = "A senha não pode estar em branco")
        @Size(min = 6, message = "A senha deve ter no mínimo 6 caracteres")
        String senha,

        @NotBlank(message = "O telefone não pode estar em branco")
        @ValidTelefone
        String telefone,

        @NotBlank(message = "O CPF não pode estar em branco")
        @ValidCPF
        String cpf,

        @NotNull(message = "O gênero não pode ser nulo")
        String genero,

        @NotBlank(message = "A data de nascimento não pode estar em branco")
        @ValidDataNascimento
        String dataNascimento,

        @NotBlank(message = "A pergunta secreta não pode estar em branco")
        @Size(max = 70, message = "A pergunta secreta não pode ter mais de 70 caracteres")
        String perguntaSecreta,

        @NotBlank(message = "A resposta secreta não pode estar em branco")
        @Size(max = 70, message = "A resposta secreta não pode ter mais de 70 caracteres")
        String respostaSecreta
        )  {}

