package br.com.lreduca.lr_educa_api.seguranca;

import br.com.lreduca.lr_educa_api.config.SecurityConfig;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;

class PasswordEncoderTests {

    private final PasswordEncoder encoder = new SecurityConfig().passwordEncoder();

    @Test
    void deveGuardarHashBcryptSemGuardarSenhaOriginal() {
        String senha = "SenhaForte123!";
        String hash = encoder.encode(senha);

        assertNotEquals(senha, hash);
        assertTrue(hash.startsWith("$2"));
        assertTrue(encoder.matches(senha, hash));
        assertFalse(encoder.matches("senha-errada", hash));
    }
}
