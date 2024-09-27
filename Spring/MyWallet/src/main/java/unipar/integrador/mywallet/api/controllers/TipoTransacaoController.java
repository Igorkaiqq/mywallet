package unipar.integrador.mywallet.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import unipar.integrador.mywallet.application.entities.TipoTransacaoEntity;
import unipar.integrador.mywallet.application.services.TipoTransacaoService;

import java.util.List;

@Controller
@RequestMapping("/api/v1/tipo-transacao")
public class TipoTransacaoController {

    @Autowired
    private TipoTransacaoService tipoTransacaoService;

    @GetMapping
    public ResponseEntity<List<TipoTransacaoEntity>> buscarTodosTiposTransacao(){
        return ResponseEntity.ok(tipoTransacaoService.findAll());
    }

}
