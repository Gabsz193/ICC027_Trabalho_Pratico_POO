package br.edu.ufam.icomp.ru_digital.entities.consumivel;

public class Comida extends Consumivel {
    private double estoqueKg;

    public Comida(String nome) {
        super(nome);
    }

    public Comida(String nome, String descricao) {
        super(nome, descricao);
    }

    public double getEstoqueKg() {
        return estoqueKg;
    }

    public void setEstoqueKg(double estoqueKg) {
        this.estoqueKg = estoqueKg;
    }
}
