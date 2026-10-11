
package br.com.lreduca.lr_educa_api.controller;

import java.util.List;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.lreduca.lr_educa_api.model.PlanejamentoAula;
import br.com.lreduca.lr_educa_api.service.PlanejamentoAulaService;

@RestController
@RequestMapping("/api/planejamentos")
public class PlanejamentoAulaController {

    private final PlanejamentoAulaService service;

    public PlanejamentoAulaController(PlanejamentoAulaService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<PlanejamentoAula> cadastrar(
            @Valid @RequestBody PlanejamentoAula planejamento) {
        PlanejamentoAula novo = service.cadastrar(planejamento);
        return ResponseEntity.status(HttpStatus.CREATED).body(novo);
    }

    @GetMapping
    public ResponseEntity<List<PlanejamentoAula>> listarTodos() {
        return ResponseEntity.ok(service.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PlanejamentoAula> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }
}