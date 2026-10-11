
package br.com.lreduca.lr_educa_api.model;

import java.time.LocalDate;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "planejamentos_aula")
public class PlanejamentoAula {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O título é obrigatório.")
    @Size(max = 150)
    @Column(nullable = false, length = 150)
    private String titulo;

    @NotBlank(message = "A descrição é obrigatória.")
    @Column(nullable = false, columnDefinition = "TEXT")
    private String descricao;

    @NotNull(message = "A data da aula é obrigatória.")
    @Column(nullable = false)
    private LocalDate dataAula;

    public PlanejamentoAula() {
    }

    public PlanejamentoAula(String titulo, String descricao, LocalDate dataAula) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.dataAula = dataAula;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public LocalDate getDataAula() {
        return dataAula;
    }

    public void setDataAula(LocalDate dataAula) {
        this.dataAula = dataAula;
    }
}