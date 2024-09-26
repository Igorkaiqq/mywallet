package unipar.integrador.mywallet.application.dto.usuario;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginDTO {
    @NotBlank(message = "O campo não pode estar vazio!")
    private String emailOuUsername;

    @NotBlank(message = "A Senha não pode estar vazia!")
    private String senha;
}
