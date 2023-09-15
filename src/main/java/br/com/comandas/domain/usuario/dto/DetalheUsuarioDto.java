package br.com.comandas.domain.usuario.dto;

import br.com.comandas.domain.usuario.Usuario;


public record DetalheUsuarioDto(Long id, String nome, String email, String telefone) {

    public DetalheUsuarioDto(Usuario dadosUsuario) {
        this(dadosUsuario.getId(), dadosUsuario.getNome(), dadosUsuario.getEmail(), dadosUsuario.getTelefone());
    }

}
