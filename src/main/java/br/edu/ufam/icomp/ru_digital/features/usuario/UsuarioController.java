package br.edu.ufam.icomp.ru_digital.features.usuario;

import br.edu.ufam.icomp.ru_digital.entities.usuario.model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {
    private final UsuarioService usuarioService;

    @Autowired
    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping
    public ResponseEntity<List<Usuario>> findAll() {
        return ResponseEntity.ok(usuarioService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> findById(@PathVariable Long id) {
        return usuarioService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/nome/{nome}")
    public ResponseEntity<Usuario> findByNome(@PathVariable String nome) {
        return usuarioService.findByNome(nome)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Usuario> create(@RequestBody Usuario usuario) {
        Usuario novoUsuario = usuarioService.registrarUsuario(usuario);
        return new ResponseEntity<>(novoUsuario, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Usuario> update(@PathVariable Long id, @RequestBody Usuario usuario) {
        return usuarioService.update(id, usuario)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (usuarioService.delete(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/{id}/saldo/adicionar")
    public ResponseEntity<Usuario> adicionarSaldo(@PathVariable Long id, @RequestParam Long valor) {
        Usuario usuario = usuarioService.adicionarSaldo(id, valor);
        return ResponseEntity.ok(usuario);
    }

    @PostMapping("/{id}/saldo/debitar")
    public ResponseEntity<Usuario> debitarSaldo(@PathVariable Long id, @RequestParam Long valor) {
        Usuario usuario = usuarioService.debitarSaldo(id, valor);
        return ResponseEntity.ok(usuario);
    }

    @GetMapping("/{id}/saldo")
    public ResponseEntity<Long> consultarSaldo(@PathVariable Long id) {
        Long saldo = usuarioService.consultarSaldo(id);
        return ResponseEntity.ok(saldo);
    }

    // Endpoints legados mantidos para compatibilidade
    @GetMapping("/usuario")
    public Optional<Usuario> getUsuario(@RequestParam String nome) {
        return usuarioService.getUsuario(nome);
    }

    @PostMapping("/usuario")
    public Optional<Usuario> saveUsuario(@RequestBody Usuario usuario) {
        return usuarioService.saveUsuario(usuario);
    }
}
