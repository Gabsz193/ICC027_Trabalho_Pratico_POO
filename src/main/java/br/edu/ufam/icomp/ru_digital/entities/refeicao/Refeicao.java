package br.edu.ufam.icomp.ru_digital.entities.refeicao;

import br.edu.ufam.icomp.ru_digital.entities.consumivel.Consumivel;
import br.edu.ufam.icomp.ru_digital.entities.unidade.model.Unidade;
import br.edu.ufam.icomp.ru_digital.entities.usuario.model.Usuario;
import jakarta.persistence.*;
import org.springframework.lang.NonNull;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "refeicao")
public class Refeicao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoRefeicao tipo;

    @Column(nullable = false)
    private Long precoCentavos;

    @Column(nullable = false)
    private LocalDateTime dataHora;

    @ManyToOne
    @JoinColumn(name = "unidade_id", nullable = false)
    private Unidade unidade;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @ManyToMany
    @JoinTable(
        name = "refeicao_consumivel",
        joinColumns = @JoinColumn(name = "refeicao_id"),
        inverseJoinColumns = @JoinColumn(name = "consumivel_id")
    )
    private List<Consumivel> consumiveis;

    public Refeicao() {
        this.consumiveis = new ArrayList<>();
        this.dataHora = LocalDateTime.now();
    }

    public Refeicao(@NonNull String nome, @NonNull TipoRefeicao tipo, @NonNull Long precoCentavos, @NonNull Unidade unidade) {
        this.nome = nome;
        this.tipo = tipo;
        this.precoCentavos = precoCentavos;
        this.unidade = unidade;
        this.consumiveis = new ArrayList<>();
        this.dataHora = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(@NonNull String nome) {
        this.nome = nome;
    }

    public TipoRefeicao getTipo() {
        return tipo;
    }

    public void setTipo(TipoRefeicao tipo) {
        this.tipo = tipo;
    }

    public Long getPrecoCentavos() {
        return precoCentavos;
    }

    public void setPrecoCentavos(Long precoCentavos) {
        this.precoCentavos = precoCentavos;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }

    public Unidade getUnidade() {
        return unidade;
    }

    public void setUnidade(Unidade unidade) {
        this.unidade = unidade;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<Consumivel> getConsumiveis() {
        return consumiveis;
    }

    public void setConsumiveis(List<Consumivel> consumiveis) {
        this.consumiveis = consumiveis;
    }

    public void adicionarConsumivel(Consumivel consumivel) {
        this.consumiveis.add(consumivel);
    }

    public void removerConsumivel(Consumivel consumivel) {
        this.consumiveis.remove(consumivel);
    }
}
