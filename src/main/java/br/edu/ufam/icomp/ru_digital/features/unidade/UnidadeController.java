package br.edu.ufam.icomp.ru_digital.features.unidade;

import br.edu.ufam.icomp.ru_digital.entities.unidade.model.Unidade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/unidades")
public class UnidadeController {
    private final UnidadeService unidadeService;

    @Autowired
    public UnidadeController(UnidadeService unidadeService) {
        this.unidadeService = unidadeService;
    }

    @GetMapping
    public ResponseEntity<List<Unidade>> findAll() {
        return ResponseEntity.ok(unidadeService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Unidade> findById(@PathVariable Long id) {
        return unidadeService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/nome/{nome}")
    public ResponseEntity<Unidade> findByNome(@PathVariable String nome) {
        return unidadeService.findByNome(nome)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/search")
    public ResponseEntity<List<Unidade>> findByNomeContaining(@RequestParam String nome) {
        return ResponseEntity.ok(unidadeService.findByNomeContaining(nome));
    }

    @PostMapping
    public ResponseEntity<Unidade> create(@RequestBody Unidade unidade) {
        Unidade saved = unidadeService.save(unidade);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Unidade> update(@PathVariable Long id, @RequestBody Unidade unidade) {
        return unidadeService.update(id, unidade)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (unidadeService.delete(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
