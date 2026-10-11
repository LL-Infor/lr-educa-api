
package br.com.lreduca.lr_educa_api.service;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.com.lreduca.lr_educa_api.model.Aluno;
import br.com.lreduca.lr_educa_api.repository.AlunoRepository;

@Service
public class AlunoService {

    private final AlunoRepository alunoRepository;

    public AlunoService(AlunoRepository alunoRepository) {
        this.alunoRepository = alunoRepository;
    }

    public Aluno cadastrar(Aluno aluno) {
        aluno.setId(null);
        return alunoRepository.save(aluno);
    }

    public List<Aluno> listarTodos() {
        return alunoRepository.findAll();
    }

    public Aluno buscarPorId(Long id) {
        return alunoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Aluno não encontrado com o ID: " + id));
    }

    public Aluno atualizar(Long id, Aluno dados) {
        Aluno aluno = buscarPorId(id);

        aluno.setNome(dados.getNome());
        aluno.setEmail(dados.getEmail());
        aluno.setDataNascimento(dados.getDataNascimento());
        aluno.setEndereco(dados.getEndereco());
        aluno.setAtivo(dados.isAtivo());

        return alunoRepository.save(aluno);
    }

    public void excluir(Long id) {
        Aluno aluno = buscarPorId(id);
        alunoRepository.delete(aluno);
    }
}