package unipar.integrador.mywallet.api.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import unipar.integrador.mywallet.application.dto.contaBancaria.CadastroContaBancariaDTO;
import unipar.integrador.mywallet.application.entities.ContaBancariaEntity;
import unipar.integrador.mywallet.application.services.ContaBancariaService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/contas-bancarias")
public class ContaBancariaController {

    @Autowired
    private ContaBancariaService contaBancariaService;

    @PostMapping
    public ResponseEntity<ContaBancariaEntity> createContaBancaria(@RequestBody @Valid CadastroContaBancariaDTO contaBancaria) {
        ContaBancariaEntity novaConta = contaBancariaService.save(contaBancaria);
        return ResponseEntity.ok(novaConta);
    }

    @GetMapping
    public ResponseEntity<List<ContaBancariaEntity>> getAllContasBancarias() {
        List<ContaBancariaEntity> contas = contaBancariaService.findAll();
        return ResponseEntity.ok(contas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ContaBancariaEntity> getContaBancariaById(@PathVariable UUID id) {
        Optional<ContaBancariaEntity> conta = contaBancariaService.findById(id);
        return conta.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<ContaBancariaEntity> updateContaBancaria(@PathVariable UUID id, @RequestBody ContaBancariaEntity contaBancaria) {
        Optional<ContaBancariaEntity> contaExistente = contaBancariaService.findById(id);
        if (contaExistente.isPresent()) {
            contaBancaria.setId(id);
            ContaBancariaEntity contaAtualizada = contaBancariaService.update(contaBancaria);
            return ResponseEntity.ok(contaAtualizada);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
