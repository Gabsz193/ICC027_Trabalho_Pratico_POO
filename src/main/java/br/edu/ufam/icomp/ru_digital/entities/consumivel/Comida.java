package br.edu.ufam.icomp.ru_digital.entities.consumivel;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("COMIDA")
public class Comida extends Consumivel {
    /**
     * A quantidade armazenada desta comida no estoque, em quilogramas.
     */
    @Column
    private Double estoqueKg;

    public Comida() {
        super();
    }

    /**
     * Cria uma comida dado o nome.
     */
    public Comida(String nome) {
        super(nome);
    }

    /**
     * Cria uma comida dado um nome e descrição.
     */
    public Comida(String nome, String descricao) {
        super(nome, descricao);
    }


    /**
     * @return A quantidade armazenada desta comida no estoque, em quilogramas.
     */
    public Double getEstoqueKg() {
        return estoqueKg;
    }

    /**
     * Atualiza a quantidade estocada deste alimento.
     * @param estoqueKg A quantidade armazenada desta comida no estoque, em quilogramas.
     */
    public void setEstoqueKg(Double estoqueKg) {
        this.estoqueKg = estoqueKg;
    }
}
