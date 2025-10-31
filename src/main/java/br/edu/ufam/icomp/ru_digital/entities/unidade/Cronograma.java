package br.edu.ufam.icomp.ru_digital.entities.unidade;

import br.edu.ufam.icomp.ru_digital.entities.refeicao.Refeicao;
import br.edu.ufam.icomp.ru_digital.entities.unidade.Ticket.TipoRefeicao;

import java.util.*;

public class Cronograma {
    private Map<TipoRefeicao, ArrayList<Refeicao>> refeicoes;

    // Construtores
    public Cronograma() {
        this.refeicoes = new HashMap<>();
    }

    // Consulta refeição por dia da semana atual
    public Optional<Refeicao> consultarRefeicao(TipoRefeicao tipo) {
        var calendar = Calendar.getInstance();
        var diaSemana = calendar.get(Calendar.DAY_OF_WEEK);
        return this.consultarRefeicao(tipo, diaSemana - 1);
    }

    // Consulta refeição por dia da semana específico
    public Optional<Refeicao> consultarRefeicao(TipoRefeicao tipo, int diaSemana) {
        var semana = refeicoes.get(tipo);
        if (semana == null || semana.size() <= diaSemana)
            return Optional.empty();

        return Optional.of(semana.get(diaSemana));
    }

    // Getters e Setters
    public Map<TipoRefeicao, ArrayList<Refeicao>> getRefeicoes() {
        return refeicoes;
    }
}