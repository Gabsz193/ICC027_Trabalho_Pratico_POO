package br.edu.ufam.icomp.ru_digital.entities.unidade;

import br.edu.ufam.icomp.ru_digital.entities.unidade.Ticket.TipoRefeicao;
import jakarta.persistence.*;
import java.util.HashMap;
import java.util.Map;

@Entity
@Table(name = "unidade")
public class Unidade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String nome;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "cronograma_id", referencedColumnName = "id")
    private Cronograma cronograma;

    @ElementCollection
    @CollectionTable(
            name = "unidade_precos",
            joinColumns = @JoinColumn(name = "unidade_id")
    )
    @MapKeyEnumerated(EnumType.STRING)
    @MapKeyColumn(name = "tipo_refeicao")
    @Column(name = "preco_centavos", nullable = false)
    private Map<TipoRefeicao, Long> precosCentavos = new HashMap<>();

    // Construtores
    public Unidade() {
    }

    public Unidade(String nome) {
        this.nome = nome;
    }

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Cronograma getCronograma() {
        return cronograma;
    }

    public void setCronograma(Cronograma cronograma) {
        this.cronograma = cronograma;
    }

    public Map<TipoRefeicao, Long> getPrecosCentavos() {
        return precosCentavos;
    }

    public void setPrecosCentavos(Map<TipoRefeicao, Long> precosCentavos) {
        this.precosCentavos = precosCentavos;
    }

    // Método utilitário
    public Long getPrecoPorRefeicao(TipoRefeicao tipo) {
        return precosCentavos.getOrDefault(tipo, 0L);
    }
}