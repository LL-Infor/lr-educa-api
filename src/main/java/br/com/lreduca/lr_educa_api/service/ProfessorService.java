package br.com.lreduca.lr_educa_api.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

import br.com.lreduca.lr_educa_api.model.Professor;
import br.com.lreduca.lr_educa_api.repository.ProfessorRepository;

@Service
public class ProfessorService {

    private final ProfessorRepository professorRepository;

    public ProfessorService(ProfessorRepository professorRepository) {
        this.professorRepository = professorRepository;
    }

    public Professor cadastrar(Professor professor) {
        professor.setId(null);
        return professorRepository.save(professor);
    }

    public List<Professor> listarTodos() {
        return professorRepository.findAll();
    }

    public Professor buscarPorId(Long id) {
        return professorRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Professor não encontrado: " + id));
    }
}