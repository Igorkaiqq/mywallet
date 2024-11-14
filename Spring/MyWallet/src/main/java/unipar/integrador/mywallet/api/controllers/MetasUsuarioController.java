package unipar.integrador.mywallet.api.controllers;


import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import unipar.integrador.mywallet.application.dto.categoriaUsuario.CategoriaUsuarioDTO;
import unipar.integrador.mywallet.application.dto.metas.MetasUsuarioDTO;
import unipar.integrador.mywallet.application.dto.transacao.TransacaoDTO;
import unipar.integrador.mywallet.application.entities.MetasUsuarioEntity;
import unipar.integrador.mywallet.application.entities.TransacaoEntity;
import unipar.integrador.mywallet.application.interfaces.IMetasUsuario;
import unipar.integrador.mywallet.application.interfaces.ITransacao;
import unipar.integrador.mywallet.application.services.MetasUsuarioService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/metas-usuario")
public class MetasUsuarioController {

    @Autowired
    private IMetasUsuario metasService;

    @PostMapping
    public ResponseEntity<MetasUsuarioEntity> create(@Valid @RequestBody MetasUsuarioDTO dto) {
        MetasUsuarioEntity metas = metasService.save(dto);
        return new ResponseEntity<>(metas, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MetasUsuarioEntity> findById(@PathVariable UUID id) {
        Optional<MetasUsuarioEntity> metas = metasService.findById(id);
        return metas.map(ResponseEntity::ok)
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping
    public ResponseEntity<List<MetasUsuarioEntity>> findAll() {
        List<MetasUsuarioEntity> metas = metasService.findAll();
        return new ResponseEntity<>(metas, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MetasUsuarioEntity> update(@PathVariable UUID id, @Valid @RequestBody MetasUsuarioDTO dto) {
        MetasUsuarioEntity metas = metasService.update(id, dto);
        return new ResponseEntity<>(metas, HttpStatus.OK);
    }
}
