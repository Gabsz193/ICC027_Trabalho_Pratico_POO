package br.edu.ufam.icomp.ru_digital.features.refeicao;

import br.edu.ufam.icomp.ru_digital.entities.refeicao.Refeicao;
import br.edu.ufam.icomp.ru_digital.entities.refeicao.TipoRefeicao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/refeicoes")
public class RefeicaoController {
    private final RefeicaoService refeicaoService;

    @Autowired
    public RefeicaoController(RefeicaoService refeicaoService) {
        this.refeicaoService = refeicaoService;
    }

    @GetMapping
    public ResponseEntity<List<Refeicao>> findAll() {
        return ResponseEntity.ok(refeicaoService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Refeicao> findById(@PathVariable Long id) {
        return refeicaoService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/tipo/{tipo}")
    public ResponseEntity<List<Refeicao>> findByTipo(@PathVariable TipoRefeicao tipo) {
        return ResponseEntity.ok(refeicaoService.findByTipo(tipo));
    }

    @GetMapping("/unidade/{unidadeId}")
    public ResponseEntity<List<Refeicao>> findByUnidadeId(@PathVariable Long unidadeId) {
        return ResponseEntity.ok(refeicaoService.findByUnidadeId(unidadeId));
    }

    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<Refeicao>> findByUsuarioId(@PathVariable Long usuarioId) {
        return ResponseEntity.ok(refeicaoService.findByUsuarioId(usuarioId));
    }

    @PostMapping
    public ResponseEntity<Refeicao> create(@RequestBody Refeicao refeicao) {
        Refeicao saved = refeicaoService.save(refeicao);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Refeicao> update(@PathVariable Long id, @RequestBody Refeicao refeicao) {
        return refeicaoService.update(id, refeicao)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (refeicaoService.delete(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
