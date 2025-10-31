package br.edu.ufam.icomp.ru_digital.features.ticket;

import br.edu.ufam.icomp.ru_digital.entities.ticket.model.StatusTicket;
import br.edu.ufam.icomp.ru_digital.entities.ticket.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {
    List<Ticket> findByUsuarioId(Long usuarioId);
    
    List<Ticket> findByStatus(StatusTicket status);
    
    List<Ticket> findByUsuarioIdAndStatus(Long usuarioId, StatusTicket status);
    
    List<Ticket> findByUnidadeId(Long unidadeId);
    
    @Query("SELECT t FROM Ticket t WHERE t.status = :status AND t.dataValidade < :data")
    List<Ticket> findTicketsExpirados(@Param("status") StatusTicket status, @Param("data") LocalDate data);
}
