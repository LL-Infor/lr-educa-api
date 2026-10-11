
package br.com.lreduca.lr_educa_api.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "turmas")
public class Turma {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O nome da turma é obrigatório.")
    @Size(max = 100)
    @Column(nullable = false, length = 100)
    private String nome;

    @NotBlank(message = "O ano letivo é obrigatório.")
    @Size(max = 9)
    @Column(nullable = false, length = 9)
    private String anoLetivo;

    public Turma() {
    }

    public Turma(String nome, String anoLetivo) {
        this.nome = nome;
        this.anoLetivo = anoLetivo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getAnoLetivo() {
        return anoLetivo;
    }

    public void setAnoLetivo(String anoLetivo) {
        this.anoLetivo = anoLetivo;
    }
}