package br.edu.ufam.icomp.ru_digital.entities.cargo.model;

import br.edu.ufam.icomp.ru_digital.entities.unidade.model.Unidade;
import jakarta.persistence.*;

@Entity
@Table(name = "cargo")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo_cargo", discriminatorType = DiscriminatorType.STRING)
public abstract class Cargo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "unidade_id", nullable = false)
    private Unidade unidade;

    @Column(nullable = false)
    private Long salarioCentavos;

    public Cargo() {
    }

    public Cargo(Unidade unidade, Long salarioCentavos) {
        this.unidade = unidade;
        this.salarioCentavos = salarioCentavos;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Unidade getUnidade() {
        return unidade;
    }

    public void setUnidade(Unidade unidade) {
        this.unidade = unidade;
    }

    public Long getSalarioCentavos() {
        return salarioCentavos;
    }

    public void setSalarioCentavos(Long salarioCentavos) {
        this.salarioCentavos = salarioCentavos;
    }
}
