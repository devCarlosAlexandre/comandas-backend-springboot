package br.com.comandas.domain.usuario.dto;

import br.com.comandas.domain.usuario.Usuario;

public record ListagemUsuariosDto(String nome, String email, String telefone) {
    public ListagemUsuariosDto(Usuario usuario) {
        this(usuario.getNome(), usuario.getEmail(), usuario.getTelefone());
    }
}
