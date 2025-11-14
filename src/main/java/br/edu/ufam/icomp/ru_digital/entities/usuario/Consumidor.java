package br.edu.ufam.icomp.ru_digital.entities.usuario;

import br.edu.ufam.icomp.ru_digital.entities.ticket.model.Ticket;
import br.edu.ufam.icomp.ru_digital.entities.usuario.model.Usuario;
import jakarta.persistence.*;
import java.util.*;

@Entity
@Table(name = "consumidor")
public class Consumidor extends Usuario {

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

    public Consumidor(String nome, String email, String cpf, String matricula, TipoConsumidor tipo, String password) {
        super(nome, email, cpf, matricula, password);
        this.tipo = tipo;
    }

    // Getters e Setters
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
