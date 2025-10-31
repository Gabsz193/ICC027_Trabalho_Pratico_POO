package br.edu.ufam.icomp.ru_digital.entities.usuario;

import br.edu.ufam.icomp.ru_digital.entities.unidade.Ticket;
import jakarta.persistence.*;
import java.util.*;

@Entity
@Table(name = "consumidor")
public class Consumidor extends Usuario {
    /**
     * Uma lista de tickets que este consumidor possui.
     */
    @OneToMany(mappedBy = "consumidor", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Ticket> tickets = new ArrayList<>();

    /**
     * O tipo deste consumidor.
     */
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoConsumidor tipo;

    /**
     * A matrícula deste consumidor, caso seja discente.
     */
    @Column(unique = true)
    private String matricula;

    /**
     * Um conjunto de alérgenos aos quais este consumidor é sensível.
     */
    @ElementCollection
    @CollectionTable(name = "consumidor_alergenos", joinColumns = @JoinColumn(name = "consumidor_id"))
    @Column(name = "alergeno")
    private Set<String> alergenos = new HashSet<>();

    /**
     * Difere o consumidor entre discente e visitante, podendo o
     * discente comprar tickets especiais de estudante.
     */
    public enum TipoConsumidor {
        DISCENTE,
        VISITANTE
    }

    /**
     * Cria um consumidor com atributos básicos.
     * @param nome O nome real do consumidor.
     * @param email O e-mail de contato do consumidor.
     * @param cpf O CPF válido do consumidor.
     * @param tipo O tipo deste consumidor.
     */
    public Consumidor(String nome, String email, String cpf, TipoConsumidor tipo) {
        super(nome, email, cpf);
        this.tipo = tipo;
    }

    protected Consumidor() {
        super();
    }

    /**
     * @return A lista de tickets do consumidor.
     */
    public List<Ticket> getTickets() {
        return tickets;
    }

    /**
     * @return O tipo do consumidor.
     */
    public TipoConsumidor getTipo() {
        return tipo;
    }

    /**
     * Atualiza o tipo do consumidor.
     * @param tipo O novo tipo do consumidor.
     */
    public void setTipo(TipoConsumidor tipo) {
        this.tipo = tipo;
    }

    /**
     * @return O conjunto de alérgenos a qual este consumidor
     * é sensível.
     */
    public Set<String> getAlergenos() {
        return alergenos;
    }


    /**
     * @return A matrícula do consumidor, caso seja discente.
     */
    public String getMatricula() {
        return matricula;
    }

    /**
     * Atualiza a matrícula do consumidor.
     * @param matricula A nova matrícula, ou nulo.
     */
    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }
}
