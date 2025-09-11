package br.edu.ufam.icomp.ru_digital.entities.unidade;

import br.edu.ufam.icomp.ru_digital.entities.unidade.Ticket.TipoRefeicao;
import jakarta.persistence.*;
import java.util.HashMap;
import java.util.Map;

@Entity
@Table(name = "cronograma")
public class Cronograma {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Aqui, guardamos o cronograma como um Map<TipoRefeicao, String[]>
    // onde o array de 7 posições representa os dias da semana
    @ElementCollection
    @CollectionTable(
            name = "cronograma_dias",
            joinColumns = @JoinColumn(name = "cronograma_id")
    )
    @MapKeyEnumerated(EnumType.STRING)
    @MapKeyColumn(name = "tipo_refeicao")
    @Column(name = "refeicoes_semana")
    private Map<TipoRefeicao, String[]> dias = new HashMap<>();

    // Construtores
    public Cronograma() {
    }

    public Cronograma(Map<TipoRefeicao, String[]> dias) {
        this.dias = dias;
    }

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public Map<TipoRefeicao, String[]> getDias() {
        return dias;
    }

    public void setDias(Map<TipoRefeicao, String[]> dias) {
        this.dias = dias;
    }
}