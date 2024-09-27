package unipar.integrador.mywallet.application.dto.usuario;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

public record LoginDTO (

        @NotBlank(message = "O campo não pode estar vazio!")
        String emailOuUsername,

        @NotBlank(message = "A Senha não pode estar vazia!")
        String senha
    ){ }
