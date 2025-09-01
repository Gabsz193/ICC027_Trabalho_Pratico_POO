package br.edu.ufam.icomp.ru_digital.features.usuario;

import br.edu.ufam.icomp.ru_digital.entities.usuario.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api")
public class UsuarioController {
    private final UsuarioService usuarioService;

    @Autowired
    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping("/usuario")
    public Optional<Usuario> getUsuario(@RequestParam String nome) {
        return usuarioService.getUsuario(nome);
    }

    @PostMapping("/usuario")
    public Optional<Usuario> saveUsuario(@RequestBody Usuario usuario) {
        return usuarioService.saveUsuario(usuario);
    }


}
