package br.edu.ufam.icomp.ru_digital.controller;

import br.edu.ufam.icomp.ru_digital.entities.usuario.model.Usuario;
import br.edu.ufam.icomp.ru_digital.features.usuario.UsuarioService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UsuarioService usuarioService;

    public AdminController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping
    public String index(Model model) {
        List<Usuario> usuarios = usuarioService.findAll();
        model.addAttribute("usuarios", usuarios);
        return "admin";
    }

    @PostMapping("/adicionar-saldo")
    public String adicionarSaldo(@RequestParam Long usuarioId,
            @RequestParam Double valor,
            RedirectAttributes redirectAttributes) {
        try {
            // Converte valor em reais para centavos
            Long valorCentavos = (long) (valor * 100);
            usuarioService.adicionarSaldo(usuarioId, valorCentavos);
            redirectAttributes.addFlashAttribute("mensagem", "Saldo adicionado com sucesso!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("erro", "Erro ao adicionar saldo: " + e.getMessage());
        }
        return "redirect:/admin";
    }

    @PostMapping("/criar-usuario")
    public String criarUsuario(@ModelAttribute Usuario usuario, RedirectAttributes redirectAttributes) {
        try {
            usuarioService.salvarUsuario(usuario);
            redirectAttributes.addFlashAttribute("mensagem", "Usuário criado com sucesso!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("erro", "Erro ao criar usuário: " + e.getMessage());
        }
        return "redirect:/admin";
    }

    @PostMapping("/atualizar-usuario")
    public String atualizarUsuario(@ModelAttribute Usuario usuario, RedirectAttributes redirectAttributes) {
        try {
            usuarioService.salvarUsuario(usuario);
            redirectAttributes.addFlashAttribute("mensagem", "Usuário atualizado com sucesso!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("erro", "Erro ao atualizar usuário: " + e.getMessage());
        }
        return "redirect:/admin";
    }

    @PostMapping("/deletar-usuario")
    public String deletarUsuario(@RequestParam Long id, RedirectAttributes redirectAttributes) {
        try {
            usuarioService.delete(id);
            redirectAttributes.addFlashAttribute("mensagem", "Usuário excluído com sucesso!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("erro", "Erro ao excluir usuário: " + e.getMessage());
        }
        return "redirect:/admin";
    }
}
