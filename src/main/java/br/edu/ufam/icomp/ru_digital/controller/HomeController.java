package br.edu.ufam.icomp.ru_digital.controller;

import br.edu.ufam.icomp.ru_digital.entities.refeicao.TipoRefeicao;
import br.edu.ufam.icomp.ru_digital.entities.usuario.model.Usuario;
import br.edu.ufam.icomp.ru_digital.features.ticket.TicketService;
import br.edu.ufam.icomp.ru_digital.features.ticket.dto.ComprarTicketRequest;
import br.edu.ufam.icomp.ru_digital.features.ticket.dto.ConsumirTicketRequest;
import br.edu.ufam.icomp.ru_digital.features.unidade.UnidadeService;
import br.edu.ufam.icomp.ru_digital.features.usuario.UsuarioService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
import java.util.List;

@Controller
public class HomeController {

    private final TicketService ticketService;
    private final UsuarioService usuarioService;
    private final UnidadeService unidadeService;

    public HomeController(TicketService ticketService, UsuarioService usuarioService, UnidadeService unidadeService) {
        this.ticketService = ticketService;
        this.usuarioService = usuarioService;
        this.unidadeService = unidadeService;
    }

    @GetMapping("/home")
    public String home(Model model, Principal principal) {
        if (principal == null) {
            return "redirect:/login";
        }

        String username = principal.getName();
        Usuario usuario = usuarioService.findByMatricula(username)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        model.addAttribute("usuario", usuario);
        model.addAttribute("unidades", unidadeService.findAll());
        model.addAttribute("tiposRefeicao", TipoRefeicao.values());
        model.addAttribute("tickets", ticketService.listarTicketsAtivos(usuario.getId()));

        return "home";
    }

    @PostMapping("/comprar-ticket")
    public String comprarTicket(@RequestParam Long unidadeId,
            @RequestParam TipoRefeicao tipoRefeicao,
            Principal principal,
            RedirectAttributes redirectAttributes) {
        try {
            String username = principal.getName();
            Usuario usuario = usuarioService.findByMatricula(username)
                    .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

            ComprarTicketRequest request = new ComprarTicketRequest();
            request.setUsuarioId(usuario.getId());
            request.setUnidadeId(unidadeId);
            request.setTipoRefeicao(tipoRefeicao);
            request.setPrecoCentavos(250L); // Preço fixo de R$ 2,50 por enquanto

            ticketService.comprarTicket(request);
            redirectAttributes.addFlashAttribute("mensagem", "Ticket comprado com sucesso!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("erro", "Erro ao comprar ticket: " + e.getMessage());
        }

        return "redirect:/home";
    }

    @PostMapping("/consumir-ticket")
    public String consumirTicket(@RequestParam Long ticketId, RedirectAttributes redirectAttributes) {
        try {
            ConsumirTicketRequest request = new ConsumirTicketRequest();
            request.setTicketId(ticketId);
            // Consumíveis podem ser adicionados aqui se necessário, por enquanto lista
            // vazia
            request.setConsumiveisIds(List.of());

            ticketService.consumirTicket(request);
            redirectAttributes.addFlashAttribute("mensagem", "Refeição consumida com sucesso!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("erro", "Erro ao consumir ticket: " + e.getMessage());
        }

        return "redirect:/home";
    }
}
