package br.edu.ufam.icomp.ru_digital.features.ticket.dto;

import br.edu.ufam.icomp.ru_digital.entities.refeicao.TipoRefeicao;

public class ComprarTicketRequest {
    private Long usuarioId;
    private TipoRefeicao tipoRefeicao;
    private Long unidadeId;
    private Long funcionarioId;
    private Long precoCentavos;

    public ComprarTicketRequest() {
    }

    public ComprarTicketRequest(Long usuarioId, TipoRefeicao tipoRefeicao, Long unidadeId, Long funcionarioId, Long precoCentavos) {
        this.usuarioId = usuarioId;
        this.tipoRefeicao = tipoRefeicao;
        this.unidadeId = unidadeId;
        this.funcionarioId = funcionarioId;
        this.precoCentavos = precoCentavos;
    }

    public Long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
    }

    public TipoRefeicao getTipoRefeicao() {
        return tipoRefeicao;
    }

    public void setTipoRefeicao(TipoRefeicao tipoRefeicao) {
        this.tipoRefeicao = tipoRefeicao;
    }

    public Long getUnidadeId() {
        return unidadeId;
    }

    public void setUnidadeId(Long unidadeId) {
        this.unidadeId = unidadeId;
    }

    public Long getFuncionarioId() {
        return funcionarioId;
    }

    public void setFuncionarioId(Long funcionarioId) {
        this.funcionarioId = funcionarioId;
    }

    public Long getPrecoCentavos() {
        return precoCentavos;
    }

    public void setPrecoCentavos(Long precoCentavos) {
        this.precoCentavos = precoCentavos;
    }
}
