
package br.com.lreduca.lr_educa_api.controller;

import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.lreduca.lr_educa_api.repository.AlunoRepository;
import br.com.lreduca.lr_educa_api.repository.ProfessorRepository;
import br.com.lreduca.lr_educa_api.repository.TurmaRepository;
import br.com.lreduca.lr_educa_api.repository.PlanejamentoAulaRepository;

@RestController
@RequestMapping("/api/dashboard")
public class DashboardController {

    private final AlunoRepository alunoRepository;
    private final ProfessorRepository professorRepository;
    private final TurmaRepository turmaRepository;
    private final PlanejamentoAulaRepository planejamentoRepository;

    public DashboardController(
            AlunoRepository alunoRepository,
            ProfessorRepository professorRepository,
            TurmaRepository turmaRepository,
            PlanejamentoAulaRepository planejamentoRepository) {
        this.alunoRepository = alunoRepository;
        this.professorRepository = professorRepository;
        this.turmaRepository = turmaRepository;
        this.planejamentoRepository = planejamentoRepository;
    }

    @GetMapping
    public Map<String, Long> obterIndicadores() {
        return Map.of(
            "totalAlunos", alunoRepository.count(),
            "totalProfessores", professorRepository.count(),
            "totalTurmas", turmaRepository.count(),
            "totalPlanejamentos", planejamentoRepository.count()
        );
    }
}