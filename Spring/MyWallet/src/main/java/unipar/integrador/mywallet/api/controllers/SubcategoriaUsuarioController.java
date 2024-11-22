package unipar.integrador.mywallet.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import unipar.integrador.mywallet.application.dto.subcategoriaUsuario.SubcategoriaUsuarioDTO;
import unipar.integrador.mywallet.application.entities.SubcategoriaUsuarioEntity;
import unipar.integrador.mywallet.application.services.SubcategoriaUsuarioService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Controller
@RequestMapping("/api/v1/subcategoria-usuario")
public class SubcategoriaUsuarioController {

    @Autowired
    private SubcategoriaUsuarioService subcategoriaUsuarioService;

    @PostMapping
    public ResponseEntity<SubcategoriaUsuarioEntity> save(@RequestBody SubcategoriaUsuarioDTO dto) {
        SubcategoriaUsuarioEntity subcategoriaUsuario = subcategoriaUsuarioService.savePersonalizada(dto);
        return ResponseEntity.ok(subcategoriaUsuario);
    }

    @GetMapping
    public ResponseEntity<List<SubcategoriaUsuarioEntity>> buscarTodasSubcategoriasUsuarios(){
        return ResponseEntity.ok(subcategoriaUsuarioService.findAll());
    }

    @GetMapping("/categoria-usuario/{categoriaUsuarioId}")
    public ResponseEntity<Optional<List<SubcategoriaUsuarioEntity>>> buscarSubcategoriasPorUsuarioIdECategoriaUsuarioId(
            @PathVariable UUID categoriaUsuarioId) {
        return ResponseEntity.ok(subcategoriaUsuarioService.findByCategoriaUsuarioId(categoriaUsuarioId));
    }

    @PutMapping("/update")
    public ResponseEntity<SubcategoriaUsuarioEntity> update(@RequestBody SubcategoriaUsuarioDTO dto) {

        return ResponseEntity.ok(subcategoriaUsuarioService.update(dto));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        subcategoriaUsuarioService.deleteById(id);
        return ResponseEntity.noContent().build();
    }


}
