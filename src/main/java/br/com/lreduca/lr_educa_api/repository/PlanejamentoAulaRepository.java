
package br.com.lreduca.lr_educa_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import br.com.lreduca.lr_educa_api.model.PlanejamentoAula;

public interface PlanejamentoAulaRepository
        extends JpaRepository<PlanejamentoAula, Long> {
}