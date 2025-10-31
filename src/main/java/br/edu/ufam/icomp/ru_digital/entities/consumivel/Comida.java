package br.edu.ufam.icomp.ru_digital.entities.consumivel;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("COMIDA")
public class Comida extends Consumivel {

    @Column
    private Double estoqueKg;

    public Comida() {
        super();
    }

    public Comida(String nome) {
        super(nome);
    }

    public Comida(String nome, String descricao) {
        super(nome, descricao);
    }

    public Double getEstoqueKg() {
        return estoqueKg;
    }

    public void setEstoqueKg(Double estoqueKg) {
        this.estoqueKg = estoqueKg;
    }
}
