package br.edu.ufam.icomp.ru_digital.entities.consumivel;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("BEBIDA")
public class Bebida extends Consumivel {

    @Column
    private Double estoqueLitros;

    public Bebida() {
        super();
    }

    public Bebida(String nome) {
        super(nome);
    }

    public Bebida(String nome, String descricao) {
        super(nome, descricao);
    }

    public Double getEstoqueLitros() {
        return estoqueLitros;
    }

    public void setEstoqueLitros(Double estoqueLitros) {
        this.estoqueLitros = estoqueLitros;
    }
}
