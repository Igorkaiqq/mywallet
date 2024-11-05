package unipar.integrador.mywallet.application.dto.login;

import jakarta.validation.constraints.NotBlank;

public record LoginDTO (

        @NotBlank(message = "O campo não pode estar vazio!")
        String emailOuUsername,

        @NotBlank(message = "A Senha não pode estar vazia!")
        String senha
    ){ }
