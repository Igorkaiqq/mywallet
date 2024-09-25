package unipar.integrador.mywallet.application.dto.usuario;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import unipar.integrador.mywallet.application.validators.annotation.ValidTelefone;

public record AtualizarUsuarioDTO(

        @NotBlank(message = "O nome não pode estar em branco")
        @Size(max = 70, message = "O nome não pode ter mais de 70 caracteres")
        String nome,

        @NotBlank(message = "O e-mail não pode estar em branco")
        @Email(message = "E-mail inválido")
        String email,

        @NotBlank(message = "A senha não pode estar em branco")
        @Size(min = 6, message = "A senha deve ter no mínimo 6 caracteres")
        String senha,

        @NotBlank(message = "O telefone não pode estar em branco")
        @ValidTelefone
        String telefone

) {
}
