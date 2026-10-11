
package br.com.lreduca.lr_educa_api.controller;

import java.util.List;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import br.com.lreduca.lr_educa_api.model.Turma;
import br.com.lreduca.lr_educa_api.service.TurmaService;

@RestController
@RequestMapping("/api/turmas")
public class TurmaController {

    private final TurmaService turmaService;

    public TurmaController(TurmaService turmaService) {
        this.turmaService = turmaService;
    }

    @PostMapping
    public ResponseEntity<Turma> cadastrar(@Valid @RequestBody Turma turma) {
        Turma nova = turmaService.cadastrar(turma);
        return ResponseEntity.status(HttpStatus.CREATED).body(nova);
    }

    @GetMapping
    public ResponseEntity<List<Turma>> listarTodos() {
        return ResponseEntity.ok(turmaService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Turma> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(turmaService.buscarPorId(id));
    }
}