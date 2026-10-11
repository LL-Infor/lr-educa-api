
package br.com.lreduca.lr_educa_api.exception;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // Trata erros de validação, como nome vazio e e-mail inválido
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> tratarErrosValidacao(
            MethodArgumentNotValidException ex) {

        List<String> erros = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(erro -> erro.getField() + ": "
                        + erro.getDefaultMessage())
                .toList();

        Map<String, Object> resposta = Map.of(
                "status", HttpStatus.BAD_REQUEST.value(),
                "erro", "Erro de validação",
                "mensagens", erros
        );

        return ResponseEntity.badRequest().body(resposta);
    }

    // Trata JSON incorreto ou dados em formato inválido
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<Map<String, Object>> tratarJsonInvalido(
            HttpMessageNotReadableException ex) {

        Map<String, Object> resposta = Map.of(
                "status", HttpStatus.BAD_REQUEST.value(),
                "erro", "Requisição inválida",
                "mensagem", "Verifique o JSON enviado e o formato dos dados."
        );

        return ResponseEntity.badRequest().body(resposta);
    }

    // Trata recursos não encontrados, como um aluno inexistente
    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<Map<String, Object>> tratarStatusHttp(
            ResponseStatusException ex) {

        int status = ex.getStatusCode().value();

        Map<String, Object> resposta = Map.of(
                "status", status,
                "erro", status == 404
                        ? "Recurso não encontrado"
                        : "Erro na requisição",
                "mensagem", ex.getReason() != null
                        ? ex.getReason()
                        : "Não foi possível processar a requisição."
        );

        return ResponseEntity.status(ex.getStatusCode()).body(resposta);
    }
}