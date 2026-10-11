
package br.com.lreduca.lr_educa_api.service;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.com.lreduca.lr_educa_api.model.PlanejamentoAula;
import br.com.lreduca.lr_educa_api.repository.PlanejamentoAulaRepository;

@Service
public class PlanejamentoAulaService {

    private final PlanejamentoAulaRepository repository;

    public PlanejamentoAulaService(PlanejamentoAulaRepository repository) {
        this.repository = repository;
    }

    public PlanejamentoAula cadastrar(PlanejamentoAula planejamento) {
        planejamento.setId(null);
        return repository.save(planejamento);
    }

    public List<PlanejamentoAula> listarTodos() {
        return repository.findAll();
    }

    public PlanejamentoAula buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Planejamento não encontrado: " + id));
    }
}