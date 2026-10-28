package br.com.lreduca.lr_educa_api.service;

import br.com.lreduca.lr_educa_api.dto.CadastroUsuarioRequest;
import br.com.lreduca.lr_educa_api.model.PerfilAcesso;
import br.com.lreduca.lr_educa_api.model.Usuario;
import br.com.lreduca.lr_educa_api.repository.UsuarioRepository;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class AutenticacaoServiceTests {

    private final UsuarioRepository repository = mock(UsuarioRepository.class);
    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(4);
    private final AutenticacaoService service = new AutenticacaoService(repository, encoder);

    @Test
    void cadastroDeveArmazenarHashEUsarPerfilProfessor() {
        when(repository.existsByEmailIgnoreCase("professor@escola.com")).thenReturn(false);
        when(repository.save(any(Usuario.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Usuario usuario = service.cadastrar(new CadastroUsuarioRequest(
                " Professora Maria ", "PROFESSOR@ESCOLA.COM", "SenhaForte123!"
        ));

        assertEquals("Professora Maria", usuario.getNome());
        assertEquals("professor@escola.com", usuario.getEmail());
        assertNotEquals("SenhaForte123!", usuario.getSenhaHash());
        assertTrue(encoder.matches("SenhaForte123!", usuario.getSenhaHash()));
        assertEquals(PerfilAcesso.PROFESSOR, usuario.getPerfil());
        assertTrue(usuario.isAtivo());
        verify(repository).save(any(Usuario.class));
    }

    @Test
    void cadastroDeveRecusarEmailJaExistente() {
        when(repository.existsByEmailIgnoreCase("professor@escola.com")).thenReturn(true);

        assertThrows(ResponseStatusException.class, () -> service.cadastrar(
                new CadastroUsuarioRequest("Maria", "professor@escola.com", "SenhaForte123!")
        ));

        verify(repository, never()).save(any(Usuario.class));
    }
}
