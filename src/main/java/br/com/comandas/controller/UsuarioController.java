package br.com.comandas.controller;

import br.com.comandas.domain.usuario.Usuario;
import br.com.comandas.domain.usuario.UsuarioRepository;
import br.com.comandas.domain.usuario.dto.CriaUsuarioDto;
import br.com.comandas.domain.usuario.dto.DetalheUsuarioDto;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {
    @Autowired
    private UsuarioRepository repository;

    @GetMapping
    public ResponseEntity getAllUsuarios() {
        var usuarios = repository.findAll();
        return ResponseEntity.ok().body(usuarios);
    }

    @PostMapping
    @Transactional()
    public ResponseEntity createUsuarios(@RequestBody() CriaUsuarioDto usuarioDto, UriComponentsBuilder uriBuilder) {
        Usuario usuario = new Usuario(usuarioDto);
        var usuarioCriado = repository.save(usuario);
        var uri = uriBuilder.path("/usuario/{id}").buildAndExpand(usuario.getId()).toUri();
        return ResponseEntity.created(uri).body(new DetalheUsuarioDto(usuarioCriado));
    }

    @GetMapping("/{id}")
    @Transactional()
    public ResponseEntity getUsuarioByIndex(@PathVariable Long id) {
        var usuario = repository.getReferenceById(id);
        return ResponseEntity.ok(new DetalheUsuarioDto(usuario));
    }

}
