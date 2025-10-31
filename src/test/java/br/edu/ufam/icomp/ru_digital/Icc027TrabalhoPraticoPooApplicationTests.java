package br.edu.ufam.icomp.ru_digital;

import br.edu.ufam.icomp.ru_digital.entities.refeicao.Refeicao;
import br.edu.ufam.icomp.ru_digital.entities.unidade.Cronograma;
import br.edu.ufam.icomp.ru_digital.entities.unidade.Ticket;
import br.edu.ufam.icomp.ru_digital.entities.usuario.Usuario;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

class Icc027TrabalhoPraticoPooApplicationTests {

    @Test
    void contextLoads() {
        Cronograma c = new Cronograma();
        c.getRefeicoes().put(
                Ticket.TipoRefeicao.ALMOCO,
                new ArrayList<>(List.of(
                        new Refeicao("Almoço de Domingo"),
                        new Refeicao("Almoço de Segunda"),
                        new Refeicao("Almoço de Terça"),
                        new Refeicao("Almoço de Quarta"),
                        new Refeicao("Almoço de Quinta"),
                        new Refeicao("Almoço de Sexta"),
                        new Refeicao("Almoço de Sabado")
                ))
        );

        var almoco = c.consultarRefeicao(Ticket.TipoRefeicao.ALMOCO);
        if (almoco.isPresent()) {
            System.out.println(almoco.get().getNome());
        }
    }

}
