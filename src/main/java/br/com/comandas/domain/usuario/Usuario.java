package br.com.comandas.domain.usuario;

import br.com.comandas.domain.comanda.Comanda;
import br.com.comandas.domain.usuario.dto.CriaUsuarioDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity(name = "tb_usuario")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private String email;

    private String telefone;

//    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
//    private List<Comanda> comandas;

    public Usuario(CriaUsuarioDto usuarioDto) {
        this.nome = usuarioDto.nome();
        this.email = usuarioDto.email();
        this.telefone = usuarioDto.telefone();
    }
}
