package unipar.integrador.mywallet.application.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import unipar.integrador.mywallet.application.dto.login.LoginDTO;
import unipar.integrador.mywallet.application.dto.login.LoginResponseDTO;
import unipar.integrador.mywallet.application.entities.UsuarioEntity;
import unipar.integrador.mywallet.infrastructure.repository.UsuarioRepository;

import java.time.Instant;
import java.util.stream.Collectors;

@Service
public class AuthService {

    @Autowired
    JwtEncoder jwtEncoder;

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    public LoginResponseDTO authenticateAndGenerateToken(LoginDTO loginDTO) {

        UsuarioEntity usuario = usuarioRepository.findByEmailOrUsername(
                loginDTO.emailOuUsername(), loginDTO.emailOuUsername()
        ).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado"));

        if (!bCryptPasswordEncoder.matches(loginDTO.senha(), usuario.getSenha())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Senha inválida");
        }

        var expiresAt = 300L;
        var token = generateToken(usuario, expiresAt);

        return new LoginResponseDTO(token, expiresAt);

    }

    private String generateToken(UsuarioEntity usuario, long expiresAt) {
        var now = Instant.now();
        var scopes = usuario.getRoles().stream()
                .map(role -> role.getNome())
                .collect(Collectors.joining(" "));

        var claims = JwtClaimsSet.builder()
                .issuer("https://mywallet.com")
                .subject(usuario.getId().toString())
                .issuedAt(now)
                .expiresAt(now.plusSeconds(expiresAt))
                .claim("scope", scopes)
                .build();

        return jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
    }

}
