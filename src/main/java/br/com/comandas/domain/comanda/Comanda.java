package br.com.comandas.domain.comanda;

import br.com.comandas.domain.comanda.dto.AtuaizarComanda;
import br.com.comandas.domain.comanda.item.Item;
import br.com.comandas.domain.usuario.Usuario;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity(name = "tb_comanda")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Comanda {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Item> itens;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Usuario usuario;

    private boolean aberta;

    public Comanda(Usuario usuario) {
        this.usuario = usuario;
        this.aberta = true;
    }

    public void update(AtuaizarComanda dados) {
        this.aberta = dados.aberta();
        this.itens = dados.itens();
    }
}
