package br.edu.ufam.icomp.ru_digital.entities.refeicao;

import br.edu.ufam.icomp.ru_digital.entities.consumivel.Consumivel;
import org.springframework.lang.NonNull;

import java.util.ArrayList;
import java.util.List;

public class Refeicao {
    private Long id;
    private String nome;
    private List<Consumivel> consumiveis;

    /**
     * Cria uma refeição dado seu nome.
     * @param nome O nome da refeição.
     */
    public Refeicao(@NonNull String nome) {
        // TODO: Gerar o ID automaticamente
        this.nome = nome;
        this.consumiveis = new ArrayList<Consumivel>();
    }

    /**
     * @return Um ID único da refeição.
     */
    public Long getId() { return id; };

    /**
     * @return O nome da refeição, da forma que deve ser mostrado em uma interface.
     */
    public String getNome() { return nome; };

    /**
     * Define o nome da refeição, da forma que deve ser mostrado em uma interface.
     * @param nome O novo nome da refeição.
     */
    public void setNome(@NonNull String nome) { this.nome = nome; };

    /**
     * @return Uma lista de consumíveis disponiveis nesta refeição.
     */
    public List<Consumivel> getConsumiveis() { return consumiveis; }
}
