package br.com.lreduca.lr_educa_api.service;

import br.com.lreduca.lr_educa_api.dto.CadastroUsuarioRequest;
import br.com.lreduca.lr_educa_api.model.PerfilAcesso;
import br.com.lreduca.lr_educa_api.model.Usuario;
import br.com.lreduca.lr_educa_api.repository.UsuarioRepository;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
public class AutenticacaoService {

    private final UsuarioRepository repository;
    private final PasswordEncoder passwordEncoder;

    public AutenticacaoService(UsuarioRepository repository, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public Usuario cadastrar(CadastroUsuarioRequest request) {
        String email = request.email().trim().toLowerCase();

        if (repository.existsByEmailIgnoreCase(email)) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Já existe uma conta cadastrada com esse e-mail.");
        }

        Usuario usuario = new Usuario();
        usuario.setNome(request.nome().trim());
        usuario.setEmail(email);
        usuario.setSenhaHash(passwordEncoder.encode(request.senha()));
        // O cadastro público nunca permite que o cliente escolha perfil privilegiado.
        usuario.setPerfil(PerfilAcesso.PROFESSOR);
        usuario.setAtivo(true);

        return repository.save(usuario);
    }
}
