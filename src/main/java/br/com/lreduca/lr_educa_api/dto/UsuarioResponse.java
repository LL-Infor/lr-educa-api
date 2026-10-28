package br.com.lreduca.lr_educa_api.dto;

import br.com.lreduca.lr_educa_api.model.PerfilAcesso;
import br.com.lreduca.lr_educa_api.model.Usuario;

public record UsuarioResponse(Long id, String nome, String email, PerfilAcesso perfil, boolean ativo) {
    public static UsuarioResponse from(Usuario usuario) {
        return new UsuarioResponse(usuario.getId(), usuario.getNome(), usuario.getEmail(),
                usuario.getPerfil(), usuario.isAtivo());
    }
}
