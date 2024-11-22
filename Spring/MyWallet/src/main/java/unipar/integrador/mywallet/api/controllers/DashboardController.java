package unipar.integrador.mywallet.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import unipar.integrador.mywallet.application.dto.dashboard.BancosDTO;
import unipar.integrador.mywallet.application.dto.dashboard.ReceitasDTO;
import unipar.integrador.mywallet.application.dto.transacao.TransacaoUsuarioDTO;
import unipar.integrador.mywallet.application.interfaces.IDashboard;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/v1/dashboard")
public class DashboardController {

    @Autowired
    private IDashboard dashboard;

    @GetMapping("/resumo")
    public ResponseEntity<ReceitasDTO> getResumo(
            @RequestParam(value = "dataInicio", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataInicio,
            @RequestParam(value = "dataFim", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataFim) {

        if (dataInicio == null) {
            dataInicio = LocalDate.now().withDayOfMonth(1);
        }

        if (dataFim == null) {
            dataFim = LocalDate.now().withDayOfMonth(LocalDate.now().lengthOfMonth());
        }

        LocalDateTime inicio = dataInicio.atStartOfDay();
        LocalDateTime fim = dataFim.atTime(23, 59, 59);

        return ResponseEntity.ok(dashboard.findResumo(inicio, fim));
    }


    @GetMapping("/bancos")
    public ResponseEntity<List<BancosDTO>> getBancos() {
        return ResponseEntity.ok(dashboard.findBancos());
    }

    @GetMapping("/receitas")
    public ResponseEntity<List<TransacaoUsuarioDTO>> getMaioresReceitas(
            @RequestParam(value = "dataInicio", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataInicio,
            @RequestParam(value = "dataFim", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataFim
    ) {
        if (dataInicio == null) {
            dataInicio = LocalDate.now().withDayOfMonth(1);
        }

        if (dataFim == null) {
            dataFim = LocalDate.now().withDayOfMonth(LocalDate.now().lengthOfMonth());
        }

        LocalDateTime inicio = dataInicio.atStartOfDay();
        LocalDateTime fim = dataFim.atTime(23, 59, 59);

        return ResponseEntity.ok(dashboard.findMaioresReceitas(inicio, fim));
    }

    @GetMapping("/despesas")
    public ResponseEntity<List<TransacaoUsuarioDTO>> getMaioresDespesas(
            @RequestParam(value = "dataInicio", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataInicio,
            @RequestParam(value = "dataFim", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataFim
    ) {
        if (dataInicio == null) {
            dataInicio = LocalDate.now().withDayOfMonth(1);
        }

        if (dataFim == null) {
            dataFim = LocalDate.now().withDayOfMonth(LocalDate.now().lengthOfMonth());
        }

        LocalDateTime inicio = dataInicio.atStartOfDay();
        LocalDateTime fim = dataFim.atTime(23, 59, 59);
        return ResponseEntity.ok(dashboard.findMaioresDespesas(inicio, fim));
    }

}
