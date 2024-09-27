package unipar.integrador.mywallet.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import unipar.integrador.mywallet.application.entities.SubcategoriaUsuarioEntity;
import unipar.integrador.mywallet.application.services.SubcategoriaUsuarioService;

import java.util.List;

@Controller
@RequestMapping("/api/v1/subcategoria-usuario")
public class SubcategoriaUsuarioController {

    @Autowired
    private SubcategoriaUsuarioService subcategoriaUsuarioService;

    @GetMapping
    public ResponseEntity<List<SubcategoriaUsuarioEntity>> buscarTodasSubcategoriasUsuarios(){
        return ResponseEntity.ok(subcategoriaUsuarioService.findAll());
    }

}
