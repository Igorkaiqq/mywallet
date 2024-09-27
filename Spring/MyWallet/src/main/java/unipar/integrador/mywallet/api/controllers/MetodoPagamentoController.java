package unipar.integrador.mywallet.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import unipar.integrador.mywallet.application.entities.MetodoPagamentoEntity;
import unipar.integrador.mywallet.application.services.MetodoPagamentoService;

import java.util.List;

@Controller
@RequestMapping("/api/v1/metodo-pagamento")
public class MetodoPagamentoController {

    @Autowired
    MetodoPagamentoService metodoPagamentoService;

    @GetMapping
    public ResponseEntity<List<MetodoPagamentoEntity>>  buscarTodosMetodosPagamento(){
        return ResponseEntity.ok(metodoPagamentoService.findAll());
    }

}
