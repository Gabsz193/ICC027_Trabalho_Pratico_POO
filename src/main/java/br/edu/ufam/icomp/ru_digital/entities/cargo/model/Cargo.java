package br.edu.ufam.icomp.ru_digital.entities.cargo.model;

import br.edu.ufam.icomp.ru_digital.entities.unidade.model.Unidade;
import jakarta.persistence.*;

/**
 * Descreve as características de um cargo de um funcionário em
 * uma unidade específica.
 */
@Entity
@Table(name = "cargo")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo_cargo", discriminatorType = DiscriminatorType.STRING)
public abstract class Cargo {
    /**
     * O ID único deste cargo.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * A unidade a qual este cargo se refere.
     */
    @ManyToOne
    @JoinColumn(name = "unidade_id", nullable = false)
    private Unidade unidade;

    /**
     * O salário deste cargo do funcionário, em centavos.
     */
    @Column(nullable = false)
    private Long salarioCentavos;

    /**
     * Cria um cargo.
     * @param unidade A unidade referente a este cargo.
     * @param salarioCentavos O salário do funcionário deste cargo, em centavos.
     */
    public Cargo(Unidade unidade, Long salarioCentavos) {
        this.unidade = unidade;
        this.salarioCentavos = salarioCentavos;
    }

    protected Cargo() { }

    /**
     * @return O ID deste cargo.
     */
    public Long getId() {
        return id;
    }

    /**
     * Atualiza o ID deste cargo.
     * @param id O novo ID do cargo.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return A unidade referente a este cargo.
     */
    public Unidade getUnidade() {
        return unidade;
    }

    /**
     * Atualiza a unidade a qual este cargo se refere.
     * @param unidade A nova unidade.
     */
    public void setUnidade(Unidade unidade) {
        this.unidade = unidade;
    }

    /**
     * @return O salário do funcionário que exerce este cargo.
     */
    public Long getSalarioCentavos() {
        return salarioCentavos;
    }

    /**
     * Atualiza o salário pago por este cargo
     * @param salarioCentavos O novo salário, em centavos.
     */
    public void setSalarioCentavos(Long salarioCentavos) {
        this.salarioCentavos = salarioCentavos;
    }
}
