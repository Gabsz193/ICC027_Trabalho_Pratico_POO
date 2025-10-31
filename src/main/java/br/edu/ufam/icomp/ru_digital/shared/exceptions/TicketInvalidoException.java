package br.edu.ufam.icomp.ru_digital.shared.exceptions;

public class TicketInvalidoException extends RuntimeException {
    public TicketInvalidoException(String message) {
        super(message);
    }

    public TicketInvalidoException(Long ticketId, String motivo) {
        super(String.format("Ticket #%d inválido: %s", ticketId, motivo));
    }
}
