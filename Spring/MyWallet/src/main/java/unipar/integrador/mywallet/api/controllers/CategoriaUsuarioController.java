package unipar.integrador.mywallet.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import unipar.integrador.mywallet.application.dto.categoriaUsuario.CategoriaUsuarioDTO;
import unipar.integrador.mywallet.application.services.CategoriaUsuarioService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/categoria-usuario")
public class CategoriaUsuarioController {

    @Autowired
    private CategoriaUsuarioService categoriaUsuarioService;

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

    @GetMapping("/{usuarioId}/tipoTransacao/{tipoTransacaoId}")
    public ResponseEntity<List<CategoriaUsuarioDTO>> findCategoriasByUsuarioIdAndTipoTransacaoId(
            @PathVariable UUID usuarioId,
            @PathVariable UUID tipoTransacaoId) {

        List<CategoriaUsuarioDTO> categorias = categoriaUsuarioService.findByUsuarioIdAndTipoTransacaoId(usuarioId, tipoTransacaoId);
        return ResponseEntity.ok(categorias);
    }

}
