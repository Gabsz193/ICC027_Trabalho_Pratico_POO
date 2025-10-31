package br.edu.ufam.icomp.ru_digital.features.ticket.dto;

import java.util.List;

public class ConsumirTicketRequest {
    private Long ticketId;
    private List<Long> consumiveisIds;

    public ConsumirTicketRequest() {
    }

    public ConsumirTicketRequest(Long ticketId, List<Long> consumiveisIds) {
        this.ticketId = ticketId;
        this.consumiveisIds = consumiveisIds;
    }

    public Long getTicketId() {
        return ticketId;
    }

    public void setTicketId(Long ticketId) {
        this.ticketId = ticketId;
    }

    public List<Long> getConsumiveisIds() {
        return consumiveisIds;
    }

    public void setConsumiveisIds(List<Long> consumiveisIds) {
        this.consumiveisIds = consumiveisIds;
    }
}
