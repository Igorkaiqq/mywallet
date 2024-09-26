package unipar.integrador.mywallet.api.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import unipar.integrador.mywallet.application.dto.usuario.AtualizarUsuarioDTO;
import unipar.integrador.mywallet.application.dto.usuario.CadastroUsuarioDTO;
import unipar.integrador.mywallet.application.dto.usuario.LoginDTO;
import unipar.integrador.mywallet.application.entities.UsuarioEntity;
import unipar.integrador.mywallet.application.exception.UsuarioNaoEncontradoException;
import unipar.integrador.mywallet.application.services.UsuarioService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping
    public ResponseEntity<UsuarioEntity> criarUsuario (@Valid @RequestBody CadastroUsuarioDTO dto){
        UsuarioEntity usuario = usuarioService.save(dto);
        return ResponseEntity.ok(usuario);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioEntity> buscarUsuarioPorId(@PathVariable UUID id) {
        UsuarioEntity usuario = usuarioService.findById(id)
                .orElseThrow(() -> new UsuarioNaoEncontradoException("Usuário não encontrado"));
        return ResponseEntity.ok(usuario);
    }

    @GetMapping
    public ResponseEntity<List<UsuarioEntity>> buscarTodosUsuarios(){
        List<UsuarioEntity> usuarios = usuarioService.findAll();
        return ResponseEntity.ok(usuarios);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioEntity> atualizarUsuario(@PathVariable UUID id, @Valid @RequestBody AtualizarUsuarioDTO dto){
        UsuarioEntity usuario = usuarioService.update(id, dto);
        return ResponseEntity.ok(usuario);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> inativarUsuario(@PathVariable UUID id){
        usuarioService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/login")
    public ResponseEntity<String> validarLogin(@Valid @RequestBody LoginDTO loginDto) {
        String nomeOuEmail = loginDto.getEmailOuUsername();
        String senha = loginDto.getSenha();


        Optional<UsuarioEntity> usuarioOpt = usuarioService.findByEmailOuUsername(nomeOuEmail, nomeOuEmail);

        if (usuarioOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Username ou e-mail inválido.");
        }
        UsuarioEntity usuario = usuarioOpt.get();

        if (!usuario.getSenha().equals(senha)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Senha inválida.");
        }

        return ResponseEntity.ok("Login realizado com sucesso.");
    }



}
