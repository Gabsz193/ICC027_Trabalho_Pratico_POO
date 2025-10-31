package br.edu.ufam.icomp.ru_digital.entities.usuario;

import br.edu.ufam.icomp.ru_digital.entities.ticket.model.Ticket;
import jakarta.persistence.*;
import java.util.*;

@Entity
@Table(name = "consumidor")
public class Consumidor extends Usuario2 {

    @OneToMany(mappedBy = "consumidor", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Ticket> tickets = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoConsumidor tipo;

    @ElementCollection
    @CollectionTable(name = "consumidor_alergenos", joinColumns = @JoinColumn(name = "consumidor_id"))
    @Column(name = "alergeno")
    private Set<String> alergenos = new HashSet<>();

    public enum TipoConsumidor {
        DISCENTE,
        VISITANTE
    }

    // Construtores
    public Consumidor() {
        super();
    }

    public Consumidor(String nome, String email, String cpf, String senha, NivelPermissao nivelPermissao, TipoConsumidor tipo) {
        super(nome, email, cpf, senha, nivelPermissao);
        this.tipo = tipo;
    }

    // Getters e Setters
    public List<Ticket> getTickets() {
        return tickets;
    }

    public TipoConsumidor getTipo() {
        return tipo;
    }

    public void setTipo(TipoConsumidor tipo) {
        this.tipo = tipo;
    }

    public Set<String> getAlergenos() {
        return alergenos;
    }

    public void setAlergenos(Set<String> alergenos) {
        this.alergenos = alergenos;
    }
}
