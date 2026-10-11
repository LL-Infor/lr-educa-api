
package br.com.lreduca.lr_educa_api.controller;

import java.util.List;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.lreduca.lr_educa_api.model.Aluno;
import br.com.lreduca.lr_educa_api.service.AlunoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/alunos")
@Tag(
    name = "Alunos",
    description = "Operações de cadastro, consulta, atualização e exclusão de alunos"
)
public class AlunoController {

    private final AlunoService alunoService;

    public AlunoController(AlunoService alunoService) {
        this.alunoService = alunoService;
    }

    @PostMapping
    @Operation(
        summary = "Cadastrar aluno",
        description = "Cadastra um novo aluno após validar os dados enviados."
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "201",
            description = "Aluno cadastrado com sucesso",
            content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = Aluno.class)
            )
        ),
        @ApiResponse(
            responseCode = "400",
            description = "Dados inválidos ou erro de validação",
            content = @Content(
                mediaType = "application/json"
            )
        )
    })
    public ResponseEntity<Aluno> cadastrar(
            @Valid @RequestBody Aluno aluno) {

        Aluno novoAluno = alunoService.cadastrar(aluno);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(novoAluno);
    }
    
    @GetMapping
    @Operation(
        summary = "Listar alunos",
        description = "Retorna a lista de alunos cadastrados."
    )
    @ApiResponses({
        @ApiResponse(
            responseCode = "200",
            description = "Lista de alunos retornada com sucesso",
            content = @Content(
                mediaType = "application/json",
                array = @ArraySchema(
                    schema = @Schema(implementation = Aluno.class)
                )
            )
        )
    })
    public ResponseEntity<List<Aluno>> listarTodos() {
        return ResponseEntity.ok(alunoService.listarTodos());
    }

    @GetMapping("/{id}")
    @Operation(
        summary = "Buscar aluno por ID",
        description = "Retorna os dados de um aluno específico."
    )
    @ApiResponses({
        @ApiResponse(
            responseCode = "200",
            description = "Aluno encontrado",
            content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = Aluno.class)
            )
        ),
        @ApiResponse(
            responseCode = "404",
            description = "Aluno não encontrado",
            content = @Content(mediaType = "application/json")
        )
    })
    public ResponseEntity<Aluno> buscarPorId(
            @Parameter(
                description = "Identificador do aluno",
                example = "1"
            )
            @PathVariable Long id) {

        return ResponseEntity.ok(alunoService.buscarPorId(id));
    }

    @PutMapping("/{id}")
    @Operation(
        summary = "Atualizar aluno",
        description = "Atualiza os dados de um aluno existente."
    )
    @ApiResponses({
        @ApiResponse(
            responseCode = "200",
            description = "Aluno atualizado com sucesso",
            content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = Aluno.class)
            )
        ),
        @ApiResponse(
            responseCode = "400",
            description = "Dados inválidos ou erro de validação",
            content = @Content(mediaType = "application/json")
        ),
        @ApiResponse(
            responseCode = "404",
            description = "Aluno não encontrado",
            content = @Content(mediaType = "application/json")
        )
    })
    public ResponseEntity<Aluno> atualizar(
            @Parameter(
                description = "Identificador do aluno",
                example = "1"
            )
            @PathVariable Long id,
            @Valid @RequestBody Aluno aluno) {

        return ResponseEntity.ok(alunoService.atualizar(id, aluno));
    }

    @DeleteMapping("/{id}")
    @Operation(
        summary = "Excluir aluno",
        description = "Remove um aluno cadastrado pelo seu identificador."
    )
    @ApiResponses({
        @ApiResponse(
            responseCode = "204",
            description = "Aluno excluído com sucesso",
            content = @Content
        ),
        @ApiResponse(
            responseCode = "404",
            description = "Aluno não encontrado",
            content = @Content(mediaType = "application/json")
        )
    })
    public ResponseEntity<Void> excluir(
            @Parameter(
                description = "Identificador do aluno",
                example = "1"
            )
            @PathVariable Long id) {

        alunoService.excluir(id);

        return ResponseEntity.noContent().build();
    }
}