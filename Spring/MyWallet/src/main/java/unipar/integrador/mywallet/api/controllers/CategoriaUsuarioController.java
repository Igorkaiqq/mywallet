package unipar.integrador.mywallet.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import unipar.integrador.mywallet.application.dto.categoriaUsuario.CategoriaUsuarioDTO;
import unipar.integrador.mywallet.application.entities.CategoriaUsuarioEntity;
import unipar.integrador.mywallet.application.services.CategoriaUsuarioService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/categoria-usuario")
public class CategoriaUsuarioController {

    @Autowired
    private CategoriaUsuarioService categoriaUsuarioService;

    @PostMapping
    public ResponseEntity<CategoriaUsuarioEntity> save(@RequestBody CategoriaUsuarioDTO dto) {
        CategoriaUsuarioEntity categoriaUsuario = categoriaUsuarioService.savePersonalizada(dto);
        return ResponseEntity.ok(categoriaUsuario);
    }

    @GetMapping("")
    public ResponseEntity<List<CategoriaUsuarioDTO>> findAllCategoriasUsuariosAtivas() {
        List<CategoriaUsuarioDTO> categorias = categoriaUsuarioService.findAllCategoriasUsuariosAtivas();
        return ResponseEntity.ok(categorias);
    }


    @GetMapping("/{id}")
    public ResponseEntity<List<CategoriaUsuarioDTO>> findAllCategoriasByUsuarioId(@PathVariable UUID id) {
        List<CategoriaUsuarioDTO> categorias = categoriaUsuarioService.findByUsuarioId(id);
        return ResponseEntity.ok(categorias);
    }

    @GetMapping("/tipoTransacao/{tipoTransacaoId}")
    public ResponseEntity<List<CategoriaUsuarioDTO>> findCategoriasByUsuarioIdAndTipoTransacaoId(
            @PathVariable UUID tipoTransacaoId) {

        List<CategoriaUsuarioDTO> categorias = categoriaUsuarioService.findByUsuarioIdAndTipoTransacaoId(tipoTransacaoId);
        return ResponseEntity.ok(categorias);
    }

    @PutMapping("/update")
    public ResponseEntity<CategoriaUsuarioDTO> update(@RequestBody CategoriaUsuarioDTO dto) {
        System.out.println(dto);
        CategoriaUsuarioDTO updatedCategoria = categoriaUsuarioService.update(dto);
        return ResponseEntity.ok(updatedCategoria);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        categoriaUsuarioService.deleteById(id);
        return ResponseEntity.noContent().build();
    }


}
