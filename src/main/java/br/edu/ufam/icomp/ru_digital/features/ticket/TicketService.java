package br.edu.ufam.icomp.ru_digital.features.ticket;

import br.edu.ufam.icomp.ru_digital.entities.consumivel.Consumivel;
import br.edu.ufam.icomp.ru_digital.entities.funcionario.model.Funcionario;
import br.edu.ufam.icomp.ru_digital.entities.ticket.model.StatusTicket;
import br.edu.ufam.icomp.ru_digital.entities.ticket.model.Ticket;
import br.edu.ufam.icomp.ru_digital.entities.unidade.model.Unidade;
import br.edu.ufam.icomp.ru_digital.entities.usuario.model.Usuario;
import br.edu.ufam.icomp.ru_digital.features.consumivel.ConsumivelRepository;
import br.edu.ufam.icomp.ru_digital.features.funcionario.FuncionarioRepository;
import br.edu.ufam.icomp.ru_digital.features.ticket.dto.ComprarTicketRequest;
import br.edu.ufam.icomp.ru_digital.features.ticket.dto.ConsumirTicketRequest;
import br.edu.ufam.icomp.ru_digital.features.unidade.UnidadeRepository;
import br.edu.ufam.icomp.ru_digital.features.usuario.UsuarioService;
import br.edu.ufam.icomp.ru_digital.shared.exceptions.RecursoNaoEncontradoException;
import br.edu.ufam.icomp.ru_digital.shared.exceptions.TicketInvalidoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TicketService {
    private final TicketRepository ticketRepository;
    private final UsuarioService usuarioService;
    private final UnidadeRepository unidadeRepository;
    private final FuncionarioRepository funcionarioRepository;
    private final ConsumivelRepository consumivelRepository;

    @Autowired
    public TicketService(
            TicketRepository ticketRepository,
            UsuarioService usuarioService,
            UnidadeRepository unidadeRepository,
            FuncionarioRepository funcionarioRepository,
            ConsumivelRepository consumivelRepository
    ) {
        this.ticketRepository = ticketRepository;
        this.usuarioService = usuarioService;
        this.unidadeRepository = unidadeRepository;
        this.funcionarioRepository = funcionarioRepository;
        this.consumivelRepository = consumivelRepository;
    }

    @Transactional
    public Ticket comprarTicket(ComprarTicketRequest request) {
        // Validar e buscar entidades
        Usuario usuario = usuarioService.findById(request.getUsuarioId())
                .orElseThrow(() -> new RecursoNaoEncontradoException("Usuario", request.getUsuarioId()));

        Unidade unidade = unidadeRepository.findById(request.getUnidadeId())
                .orElseThrow(() -> new RecursoNaoEncontradoException("Unidade", request.getUnidadeId()));

        Funcionario funcionario = null;
        if (request.getFuncionarioId() != null) {
            funcionario = funcionarioRepository.findById(request.getFuncionarioId())
                    .orElseThrow(() -> new RecursoNaoEncontradoException("Funcionario", request.getFuncionarioId()));
        }

        // Validar preço
        if (request.getPrecoCentavos() == null || request.getPrecoCentavos() <= 0) {
            throw new IllegalArgumentException("Preço do ticket deve ser maior que zero");
        }

        // Debitar saldo do usuário (irá validar se tem saldo suficiente)
        usuarioService.debitarSaldo(request.getUsuarioId(), request.getPrecoCentavos());

        // Criar ticket
        Ticket ticket = new Ticket(
                usuario,
                request.getTipoRefeicao(),
                unidade,
                request.getPrecoCentavos(),
                funcionario
        );

        return ticketRepository.save(ticket);
    }

    @Transactional
    public Ticket consumirTicket(ConsumirTicketRequest request) {
        // Buscar ticket
        Ticket ticket = ticketRepository.findById(request.getTicketId())
                .orElseThrow(() -> new RecursoNaoEncontradoException("Ticket", request.getTicketId()));

        // Validar status do ticket
        if (ticket.getStatus() != StatusTicket.ATIVO) {
            throw new TicketInvalidoException(ticket.getId(), "Ticket já foi consumido ou está expirado");
        }

        // Validar validade do ticket
        if (LocalDate.now().isAfter(ticket.getDataValidade())) {
            ticket.setStatus(StatusTicket.EXPIRADO);
            ticketRepository.save(ticket);
            throw new TicketInvalidoException(ticket.getId(), "Ticket expirado");
        }

        // Buscar consumíveis selecionados
        List<Consumivel> consumiveisSelecionados = new ArrayList<>();
        if (request.getConsumiveisIds() != null && !request.getConsumiveisIds().isEmpty()) {
            for (Long consumivelId : request.getConsumiveisIds()) {
                Consumivel consumivel = consumivelRepository.findById(consumivelId)
                        .orElseThrow(() -> new RecursoNaoEncontradoException("Consumivel", consumivelId));
                consumiveisSelecionados.add(consumivel);
            }
        }

        // Consumir ticket
        ticket.consumir(consumiveisSelecionados);

        return ticketRepository.save(ticket);
    }

    public List<Ticket> listarTicketsUsuario(Long usuarioId) {
        return ticketRepository.findByUsuarioId(usuarioId);
    }

    public List<Ticket> listarTicketsAtivos(Long usuarioId) {
        return ticketRepository.findByUsuarioIdAndStatus(usuarioId, StatusTicket.ATIVO);
    }

    public Optional<Ticket> buscarPorId(Long id) {
        return ticketRepository.findById(id);
    }

    public List<Ticket> listarTodos() {
        return ticketRepository.findAll();
    }

    public List<Ticket> listarPorUnidade(Long unidadeId) {
        return ticketRepository.findByUnidadeId(unidadeId);
    }

    public List<Ticket> listarPorStatus(StatusTicket status) {
        return ticketRepository.findByStatus(status);
    }

    @Transactional
    public void verificarExpirados() {
        List<Ticket> ticketsExpirados = ticketRepository.findTicketsExpirados(
                StatusTicket.ATIVO,
                LocalDate.now()
        );

        for (Ticket ticket : ticketsExpirados) {
            ticket.setStatus(StatusTicket.EXPIRADO);
            ticketRepository.save(ticket);
        }
    }
}
