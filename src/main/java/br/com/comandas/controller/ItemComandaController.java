package br.com.comandas.controller;

import br.com.comandas.domain.comanda.ComandaRepository;
import br.com.comandas.domain.comanda.item.Item;
import br.com.comandas.domain.comanda.item.ItemRepository;
import br.com.comandas.domain.comanda.item.dto.CriaItemDto;
import br.com.comandas.domain.comanda.item.dto.DetalheItem;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/comandas")
public class ItemComandaController {
    @Autowired
    ItemRepository itemRepository;

    @Autowired
    ComandaRepository comandaRepository;

    @GetMapping("/{id}/itens")
    @Transactional()
    public ResponseEntity getAllItens(@PathVariable Long id) {
        var itens = itemRepository.findAllByComandaId(id).stream().map(DetalheItem::new);
        return ResponseEntity.ok(itens);
    }

    @PostMapping("/{id}/itens")
    @Transactional()
    public ResponseEntity createItem(@PathVariable Long id, @RequestBody() CriaItemDto dados, UriComponentsBuilder uriBuilder) {
        var comanda = comandaRepository.getReferenceById(id);

        if (comanda == null) {
            return ResponseEntity.notFound().build();
        }

        Item item = new Item(comanda, dados);
        itemRepository.save(item);
        var uri = uriBuilder.path("/itens/{id}").buildAndExpand(item.getId()).toUri();
        return ResponseEntity.created(uri).body(new DetalheItem(item));
    }
}
