package br.com.lreduca.lr_educa_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import br.com.lreduca.lr_educa_api.model.Professor;

public interface ProfessorRepository extends JpaRepository<Professor, Long> {
}