package br.edu.ufam.icomp.ru_digital.entities.consumivel;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("BEBIDA")
public class Bebida extends Consumivel {
    /**
     * A quantidade armazenada desta bebida no estoque, em litros.
     */
    @Column
    private Double estoqueLitros;

    public Bebida() {
        super();
    }

    /**
     * Inicializa uma bebida dado o nome.
     */
    public Bebida(String nome) {
        super(nome);
    }

    /**
     * Inicializa uma bebida dado o nome e descrição.
     */
    public Bebida(String nome, String descricao) {
        super(nome, descricao);
    }

    /**
     * @return A quantidade armazenada desta bebida no estoque, em litros.
     */
    public Double getEstoqueLitros() {
        return estoqueLitros;
    }

    /**
     * Atualiza o estoque armazenado desta bebida.
     * @param estoqueLitros A quantidade armazenada desta bebida no estoque, em litros.
     */
    public void setEstoqueLitros(Double estoqueLitros) {
        this.estoqueLitros = estoqueLitros;
    }
}
