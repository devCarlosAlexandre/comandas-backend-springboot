package br.com.comandas.domain.comanda.dto;

import br.com.comandas.domain.comanda.Comanda;
import br.com.comandas.domain.usuario.Usuario;


public record DetalheComandaDto(Long id, Usuario usuario, Boolean aberta) {
    public DetalheComandaDto(Comanda comanda) {
        this(comanda.getId(), comanda.getUsuario(), comanda.isAberta());
    }

    public DetalheComandaDto(Comanda comanda, Usuario usuario) {
        this(comanda.getId(), usuario, comanda.isAberta());
    }
}
