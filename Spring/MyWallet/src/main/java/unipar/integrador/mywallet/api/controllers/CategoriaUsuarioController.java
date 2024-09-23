package unipar.integrador.mywallet.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import unipar.integrador.mywallet.application.dto.categoriaUsuario.CategoriaUsuarioDTO;
import unipar.integrador.mywallet.application.services.CategoriaPadraoService;
import unipar.integrador.mywallet.application.services.CategoriaUsuarioService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/categoria-usuario")
public class CategoriaUsuarioController {

    @Autowired
    private CategoriaUsuarioService categoriaUsuarioService;

    @GetMapping("/categorias-ativas")
    public ResponseEntity<List<CategoriaUsuarioDTO>> findAllCategoriasUsuariosAtivas() {
        List<CategoriaUsuarioDTO> categorias = categoriaUsuarioService.findAllCategoriasUsuariosAtivas();
        return ResponseEntity.ok(categorias);
    }


}
