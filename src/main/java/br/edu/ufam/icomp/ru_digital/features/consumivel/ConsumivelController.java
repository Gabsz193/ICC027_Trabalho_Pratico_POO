package br.edu.ufam.icomp.ru_digital.features.consumivel;

import br.edu.ufam.icomp.ru_digital.entities.consumivel.Consumivel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/consumiveis")
public class ConsumivelController {
    private final ConsumivelService consumivelService;

    @Autowired
    public ConsumivelController(ConsumivelService consumivelService) {
        this.consumivelService = consumivelService;
    }

    @GetMapping
    public ResponseEntity<List<Consumivel>> findAll() {
        return ResponseEntity.ok(consumivelService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Consumivel> findById(@PathVariable Long id) {
        return consumivelService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/search")
    public ResponseEntity<List<Consumivel>> findByNome(@RequestParam String nome) {
        return ResponseEntity.ok(consumivelService.findByNome(nome));
    }

    @PostMapping
    public ResponseEntity<Consumivel> create(@RequestBody Consumivel consumivel) {
        Consumivel saved = consumivelService.save(consumivel);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Consumivel> update(@PathVariable Long id, @RequestBody Consumivel consumivel) {
        return consumivelService.update(id, consumivel)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (consumivelService.delete(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
