package unipar.integrador.mywallet.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import unipar.integrador.mywallet.application.dto.CadastroUsuarioDto;
import unipar.integrador.mywallet.application.entities.UsuarioEntity;
import unipar.integrador.mywallet.application.services.UsuarioService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping
    public ResponseEntity<UsuarioEntity> create (@RequestBody CadastroUsuarioDto dto){
        UsuarioEntity usuario = usuarioService.save(dto);
        return ResponseEntity.ok(usuario);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioEntity> findById(@PathVariable UUID id){
        Optional<UsuarioEntity> usuario = usuarioService.findById(id);
        return usuario.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<UsuarioEntity>> findAll(){
        List<UsuarioEntity> usuarios = usuarioService.findAll();
        return ResponseEntity.ok(usuarios);
    }

    @PutMapping
    public ResponseEntity<UsuarioEntity> update(@RequestBody UsuarioEntity usuarioEntity){
        UsuarioEntity usuario = usuarioService.update(usuarioEntity);
        return ResponseEntity.ok(usuario);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable UUID id){
        usuarioService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
