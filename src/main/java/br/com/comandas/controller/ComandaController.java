package br.com.comandas.controller;

import br.com.comandas.domain.comanda.Comanda;
import br.com.comandas.domain.comanda.ComandaRepository;
import br.com.comandas.domain.comanda.dto.AtuaizarComanda;
import br.com.comandas.domain.comanda.dto.CriaComandaDto;
import br.com.comandas.domain.comanda.dto.DetalheComandaDto;
import br.com.comandas.domain.usuario.UsuarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.print.attribute.standard.PresentationDirection;

@RestController
@RequestMapping("/comandas")
public class ComandaController {

    @Autowired
    ComandaRepository repository;

    @Autowired
    UsuarioRepository usuarioRepository;

    @GetMapping
    public ResponseEntity getAllComandasAbertas() {
        var comandas = repository.findAllByAbertaTrue();
        return ResponseEntity.ok(comandas);
    }

    @PostMapping()
    @Transactional()
    public ResponseEntity createComanda(@RequestBody() CriaComandaDto dados, UriComponentsBuilder uriBuilder) {
        var usuario = usuarioRepository.getReferenceById(dados.usuario_id());

        if (usuario == null) {
            return ResponseEntity.badRequest().build();
        }

        Comanda comanda = new Comanda(usuario);
        repository.save(comanda);
        var uri = uriBuilder.path("/comandas/{id}").buildAndExpand(comanda.getId()).toUri();

        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    @Transactional()
    public ResponseEntity getByIndexComanda(@PathVariable() Long id) {
        var comanda = repository.getReferenceById(id);

        if (comanda == null) return ResponseEntity.notFound().build();

        return ResponseEntity.ok(new DetalheComandaDto(comanda));
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity updateComanda(@PathVariable Long id, @RequestBody() AtuaizarComanda dados) {
        var comanda = repository.getReferenceById(id);

        if (comanda == null) return ResponseEntity.notFound().build();

        comanda.update(dados);
        return ResponseEntity.ok(new DetalheComandaDto(comanda));
    }
}
