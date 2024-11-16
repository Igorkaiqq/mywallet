package unipar.integrador.mywallet.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import unipar.integrador.mywallet.application.dto.dashboard.BancosDTO;
import unipar.integrador.mywallet.application.dto.dashboard.MaioresTransacoesDTO;
import unipar.integrador.mywallet.application.dto.dashboard.ReceitasDTO;
import unipar.integrador.mywallet.application.dto.transacao.TransacaoUsuarioDTO;
import unipar.integrador.mywallet.application.interfaces.IDashboard;

import java.util.List;

@RestController
@RequestMapping("/api/v1/dashboard")
public class DashboardController {

    @Autowired
    private IDashboard dashboard;

    @GetMapping("/resumo")
    public ResponseEntity<ReceitasDTO> getResumo() {
        return ResponseEntity.ok(dashboard.findResumo());
    }

    @GetMapping("/bancos")
    public ResponseEntity<List<BancosDTO>> getBancos() {
        return ResponseEntity.ok(dashboard.findBancos());
    }

    @GetMapping("/receitas")
    public ResponseEntity<List<TransacaoUsuarioDTO>> getMaioresReceitas() {
        return ResponseEntity.ok(dashboard.findMaioresReceitas());
    }

    @GetMapping("/despesas")
    public ResponseEntity<List<TransacaoUsuarioDTO>> getMaioresDespesas() {
        return ResponseEntity.ok(dashboard.findMaioresDespesas());
    }

}
