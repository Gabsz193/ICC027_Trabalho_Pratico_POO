package br.edu.ufam.icomp.ru_digital.entities.consumivel;

public class Bebida extends Consumivel {
    private double estoqueLitros;

    public Bebida(String nome) {
        super(nome);
    }

    public Bebida(String nome, String descricao) {
        super(nome, descricao);
    }

    public double getEstoqueLitros() {
        return estoqueLitros;
    }

    public void setEstoqueLitros(double estoqueLitros) {
        this.estoqueLitros = estoqueLitros;
    }
}
