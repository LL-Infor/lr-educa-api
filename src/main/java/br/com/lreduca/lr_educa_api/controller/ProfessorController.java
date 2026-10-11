package br.com.lreduca.lr_educa_api.controller;

import java.util.List;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.lreduca.lr_educa_api.model.Professor;
import br.com.lreduca.lr_educa_api.service.ProfessorService;

@RestController
@RequestMapping("/api/professores")
public class ProfessorController {

    private final ProfessorService professorService;

    public ProfessorController(ProfessorService professorService) {
        this.professorService = professorService;
    }

    @PostMapping
    public ResponseEntity<Professor> cadastrar(
            @Valid @RequestBody Professor professor) {
        Professor novo = professorService.cadastrar(professor);
        return ResponseEntity.status(HttpStatus.CREATED).body(novo);
    }

    @GetMapping
    public ResponseEntity<List<Professor>> listarTodos() {
        return ResponseEntity.ok(professorService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Professor> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(professorService.buscarPorId(id));
    }
}