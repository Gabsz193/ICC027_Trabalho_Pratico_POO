package br.edu.ufam.icomp.ru_digital.entities.unidade;

import br.edu.ufam.icomp.ru_digital.entities.usuario.Consumidor;
import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "ticket")
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoRefeicao tipo;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date dataCompra;

    @ManyToOne(optional = false)
    @JoinColumn(name = "unidade_id")
    private Unidade unidade;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "consumidor_id")
    private Consumidor consumidor;

    @Column(nullable = false)
    private boolean valido = true;

    public enum TipoRefeicao {
        CAFE,
        ALMOCO,
        JANTAR
    }

    // Construtores
    public Ticket() {
    }

    public Ticket(TipoRefeicao tipo, Unidade unidade, Consumidor consumidor) {
        this.tipo = tipo;
        this.unidade = unidade;
        this.consumidor = consumidor;
        this.dataCompra = new Date();
        this.valido = true;
    }

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public TipoRefeicao getTipo() {
        return tipo;
    }

    public Date getDataCompra() {
        return dataCompra;
    }

    public Unidade getUnidade() {
        return unidade;
    }

    public Consumidor getConsumidor() {
        return consumidor;
    }

    public boolean isValido() {
        return valido;
    }
}
