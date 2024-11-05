package unipar.integrador.mywallet.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import unipar.integrador.mywallet.application.dto.login.LoginDTO;
import unipar.integrador.mywallet.application.dto.login.LoginResponseDTO;
import unipar.integrador.mywallet.application.services.UsuarioService;

@RestController
@RequestMapping("/api/v1/token")
public class TokenController {

    private final JwtEncoder jwtEncoder;

    @Autowired
    private UsuarioService usuarioService;

    public TokenController(JwtEncoder jwtEncoder) {
        this.jwtEncoder = jwtEncoder;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginDTO loginDTO) {
        return ResponseEntity.ok(usuarioService.realizarLogin(loginDTO));
    }
}

