package br.com.comandas.domain.comanda.dto;

import br.com.comandas.domain.comanda.item.Item;
import br.com.comandas.domain.usuario.Usuario;

import java.util.List;

public record AtuaizarComanda(List<Item> itens, Usuario usuario, Boolean aberta) {
}
