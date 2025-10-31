package br.edu.ufam.icomp.ru_digital.entities.usuario;

import br.edu.ufam.icomp.ru_digital.entities.usuario.Usuario;
import br.edu.ufam.icomp.ru_digital.entities.unidade.Ticket;
import jakarta.persistence.*;
import java.util.*;

public class Consumidor extends Usuario {
    private TipoConsumidor tipo;
    private List<Ticket> tickets = new ArrayList<>();
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
}
