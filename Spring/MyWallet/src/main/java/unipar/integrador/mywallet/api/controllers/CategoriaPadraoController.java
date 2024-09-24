package unipar.integrador.mywallet.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import unipar.integrador.mywallet.application.dto.categoriaPadrao.CategoriaPadraoDTO;
import unipar.integrador.mywallet.application.services.CategoriaPadraoService;

import java.util.List;

@RestController
@RequestMapping("/api/categoria-padrao")
public class CategoriaPadraoController {

    @Autowired
    private CategoriaPadraoService categoriaPadraoService;

    @GetMapping("/categorias-ativas")
    public ResponseEntity<List<CategoriaPadraoDTO>> findAllCategoriasAtivas(){
        List<CategoriaPadraoDTO> categorias = categoriaPadraoService.findAllCategoriasAtivas();
        return ResponseEntity.ok(categorias);
    }

}
