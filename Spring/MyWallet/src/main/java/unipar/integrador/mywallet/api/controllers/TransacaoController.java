package unipar.integrador.mywallet.api.controllers;

import jakarta.validation.Valid;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import unipar.integrador.mywallet.application.dto.transacao.TransacaoDTO;
import unipar.integrador.mywallet.application.dto.transacao.TransacaoUsuarioDTO;
import unipar.integrador.mywallet.application.entities.TransacaoEntity;
import unipar.integrador.mywallet.application.interfaces.ITransacao;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/transacao")
public class TransacaoController {

    @Autowired
    private ITransacao transacaoService;

    @PostMapping
    public ResponseEntity<TransacaoEntity> create(@Valid @RequestBody TransacaoDTO dto) {
        TransacaoEntity transacao = transacaoService.save(dto);
        return new ResponseEntity<>(transacao, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TransacaoEntity> findById(@PathVariable UUID id) {
        Optional<TransacaoEntity> transacao = transacaoService.findById(id);
        return transacao.map(ResponseEntity::ok)
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping
    public ResponseEntity<List<TransacaoEntity>> findAll() {
        List<TransacaoEntity> transacoes = transacaoService.findAll();
        return new ResponseEntity<>(transacoes, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TransacaoEntity> update(@PathVariable UUID id, @Valid @RequestBody TransacaoDTO dto) {
        TransacaoEntity updatedTransacao = transacaoService.update(id, dto);
        return new ResponseEntity<>(updatedTransacao, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        transacaoService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/usuario")
    public ResponseEntity<List<TransacaoUsuarioDTO>> buscarTransacoesUsuario() {
        return ResponseEntity.ok(transacaoService.findByUsuarioId());
    }

}
