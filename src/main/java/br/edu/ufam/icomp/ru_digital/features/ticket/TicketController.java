package br.edu.ufam.icomp.ru_digital.features.ticket;

import br.edu.ufam.icomp.ru_digital.entities.ticket.model.StatusTicket;
import br.edu.ufam.icomp.ru_digital.entities.ticket.model.Ticket;
import br.edu.ufam.icomp.ru_digital.features.ticket.dto.ComprarTicketRequest;
import br.edu.ufam.icomp.ru_digital.features.ticket.dto.ConsumirTicketRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tickets")
public class TicketController {
    private final TicketService ticketService;

    @Autowired
    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @PostMapping("/comprar")
    public ResponseEntity<Ticket> comprarTicket(@RequestBody ComprarTicketRequest request) {
        Ticket ticket = ticketService.comprarTicket(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(ticket);
    }

    @PostMapping("/consumir")
    public ResponseEntity<Ticket> consumirTicket(@RequestBody ConsumirTicketRequest request) {
        Ticket ticket = ticketService.consumirTicket(request);
        return ResponseEntity.ok(ticket);
    }

    @GetMapping
    public ResponseEntity<List<Ticket>> listarTodos() {
        return ResponseEntity.ok(ticketService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Ticket> buscarPorId(@PathVariable Long id) {
        return ticketService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<Ticket>> listarTicketsUsuario(@PathVariable Long usuarioId) {
        return ResponseEntity.ok(ticketService.listarTicketsUsuario(usuarioId));
    }

    @GetMapping("/usuario/{usuarioId}/ativos")
    public ResponseEntity<List<Ticket>> listarTicketsAtivos(@PathVariable Long usuarioId) {
        return ResponseEntity.ok(ticketService.listarTicketsAtivos(usuarioId));
    }

    @GetMapping("/unidade/{unidadeId}")
    public ResponseEntity<List<Ticket>> listarPorUnidade(@PathVariable Long unidadeId) {
        return ResponseEntity.ok(ticketService.listarPorUnidade(unidadeId));
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<Ticket>> listarPorStatus(@PathVariable StatusTicket status) {
        return ResponseEntity.ok(ticketService.listarPorStatus(status));
    }

    @PostMapping("/verificar-expirados")
    public ResponseEntity<Void> verificarExpirados() {
        ticketService.verificarExpirados();
        return ResponseEntity.ok().build();
    }
}
