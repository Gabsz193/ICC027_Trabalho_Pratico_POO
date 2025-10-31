package br.edu.ufam.icomp.ru_digital.features.unidade;

import br.edu.ufam.icomp.ru_digital.entities.unidade.model.Unidade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UnidadeService {
    private final UnidadeRepository unidadeRepository;

    @Autowired
    public UnidadeService(UnidadeRepository unidadeRepository) {
        this.unidadeRepository = unidadeRepository;
    }

    public List<Unidade> findAll() {
        return unidadeRepository.findAll();
    }

    public Optional<Unidade> findById(Long id) {
        return unidadeRepository.findById(id);
    }

    public Optional<Unidade> findByNome(String nome) {
        return unidadeRepository.findByNome(nome);
    }

    public List<Unidade> findByNomeContaining(String nome) {
        return unidadeRepository.findByNomeContainingIgnoreCase(nome);
    }

    public Unidade save(Unidade unidade) {
        return unidadeRepository.save(unidade);
    }

    public Optional<Unidade> update(Long id, Unidade unidadeAtualizada) {
        return unidadeRepository.findById(id).map(unidade -> {
            unidade.setNome(unidadeAtualizada.getNome());
            unidade.setCronograma(unidadeAtualizada.getCronograma());
            unidade.setEndereco(unidadeAtualizada.getEndereco());
            return unidadeRepository.save(unidade);
        });
    }

    public boolean delete(Long id) {
        if (unidadeRepository.existsById(id)) {
            unidadeRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
