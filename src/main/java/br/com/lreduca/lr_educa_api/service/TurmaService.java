
package br.com.lreduca.lr_educa_api.service;

import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import br.com.lreduca.lr_educa_api.model.Turma;
import br.com.lreduca.lr_educa_api.repository.TurmaRepository;

@Service
public class TurmaService {

    private final TurmaRepository turmaRepository;

    public TurmaService(TurmaRepository turmaRepository) {
        this.turmaRepository = turmaRepository;
    }

    public Turma cadastrar(Turma turma) {
        turma.setId(null);
        return turmaRepository.save(turma);
    }

    public List<Turma> listarTodos() {
        return turmaRepository.findAll();
    }

    public Turma buscarPorId(Long id) {
        return turmaRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Turma não encontrada: " + id));
    }
}