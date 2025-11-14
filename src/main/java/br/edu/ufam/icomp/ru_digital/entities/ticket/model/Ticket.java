package br.edu.ufam.icomp.ru_digital.entities.ticket.model;

import br.edu.ufam.icomp.ru_digital.entities.consumivel.Consumivel;
import br.edu.ufam.icomp.ru_digital.entities.funcionario.model.Funcionario;
import br.edu.ufam.icomp.ru_digital.entities.refeicao.TipoRefeicao;
import br.edu.ufam.icomp.ru_digital.entities.unidade.model.Unidade;
import br.edu.ufam.icomp.ru_digital.entities.usuario.Consumidor;
import br.edu.ufam.icomp.ru_digital.entities.usuario.model.Usuario;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "ticket")
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoRefeicao tipoRefeicao;

    @ManyToOne
    @JoinColumn(name = "unidade_id", nullable = false)
    private Unidade unidade;

    @Column(nullable = false)
    private Long precoCentavos;

    @Column(nullable = false)
    private LocalDateTime dataCompra;

    @Column(nullable = false)
    private LocalDate dataValidade;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusTicket status;

    @ManyToOne
    @JoinColumn(name = "funcionario_vendedor_id")
    private Funcionario funcionarioVendedor;

    @Column
    private LocalDateTime dataConsumo;

    @ManyToMany
    @JoinTable(
            name = "ticket_consumivel",
            joinColumns = @JoinColumn(name = "ticket_id"),
            inverseJoinColumns = @JoinColumn(name = "consumivel_id")
    )
    private List<Consumivel> consumiveis;

    public Ticket() {
        this.dataCompra = LocalDateTime.now();
        this.status = StatusTicket.ATIVO;
        this.consumiveis = new ArrayList<>();
    }

    public Ticket(Usuario usuario, TipoRefeicao tipoRefeicao, Unidade unidade, Long precoCentavos, Funcionario funcionarioVendedor) {
        this.usuario = usuario;
        this.tipoRefeicao = tipoRefeicao;
        this.unidade = unidade;
        this.precoCentavos = precoCentavos;
        this.funcionarioVendedor = funcionarioVendedor;
        this.dataCompra = LocalDateTime.now();
        this.dataValidade = LocalDate.now().plusDays(30); // Ticket válido por 30 dias
        this.status = StatusTicket.ATIVO;
        this.consumiveis = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public TipoRefeicao getTipoRefeicao() {
        return tipoRefeicao;
    }

    public void setTipoRefeicao(TipoRefeicao tipoRefeicao) {
        this.tipoRefeicao = tipoRefeicao;
    }

    public Unidade getUnidade() {
        return unidade;
    }

    public void setUnidade(Unidade unidade) {
        this.unidade = unidade;
    }

    public Long getPrecoCentavos() {
        return precoCentavos;
    }

    public void setPrecoCentavos(Long precoCentavos) {
        this.precoCentavos = precoCentavos;
    }

    public LocalDateTime getDataCompra() {
        return dataCompra;
    }

    public void setDataCompra(LocalDateTime dataCompra) {
        this.dataCompra = dataCompra;
    }

    public LocalDate getDataValidade() {
        return dataValidade;
    }

    public void setDataValidade(LocalDate dataValidade) {
        this.dataValidade = dataValidade;
    }

    public StatusTicket getStatus() {
        return status;
    }

    public void setStatus(StatusTicket status) {
        this.status = status;
    }

    public Funcionario getFuncionarioVendedor() {
        return funcionarioVendedor;
    }

    public void setFuncionarioVendedor(Funcionario funcionarioVendedor) {
        this.funcionarioVendedor = funcionarioVendedor;
    }

    public LocalDateTime getDataConsumo() {
        return dataConsumo;
    }

    public void setDataConsumo(LocalDateTime dataConsumo) {
        this.dataConsumo = dataConsumo;
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

    public boolean isValido() {
        return this.status == StatusTicket.ATIVO && !LocalDate.now().isAfter(this.dataValidade);
    }

    public void consumir(List<Consumivel> consumiveisSelecionados) {
        this.status = StatusTicket.CONSUMIDO;
        this.dataConsumo = LocalDateTime.now();
        if (consumiveisSelecionados != null) {
            this.consumiveis.addAll(consumiveisSelecionados);
        }
    }
}
