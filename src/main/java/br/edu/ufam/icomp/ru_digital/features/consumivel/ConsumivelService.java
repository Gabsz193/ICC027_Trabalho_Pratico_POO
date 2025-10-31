package br.edu.ufam.icomp.ru_digital.features.consumivel;

import br.edu.ufam.icomp.ru_digital.entities.consumivel.Consumivel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ConsumivelService {
    private final ConsumivelRepository consumivelRepository;

    @Autowired
    public ConsumivelService(ConsumivelRepository consumivelRepository) {
        this.consumivelRepository = consumivelRepository;
    }

    public List<Consumivel> findAll() {
        return consumivelRepository.findAll();
    }

    public Optional<Consumivel> findById(Long id) {
        return consumivelRepository.findById(id);
    }

    public List<Consumivel> findByNome(String nome) {
        return consumivelRepository.findByNomeContainingIgnoreCase(nome);
    }

    public Consumivel save(Consumivel consumivel) {
        return consumivelRepository.save(consumivel);
    }

    public Optional<Consumivel> update(Long id, Consumivel consumivelAtualizado) {
        return consumivelRepository.findById(id).map(consumivel -> {
            consumivel.setNome(consumivelAtualizado.getNome());
            consumivel.setDescricao(consumivelAtualizado.getDescricao());
            if (consumivelAtualizado.getAlergenos() != null) {
                consumivel.setAlergenos(consumivelAtualizado.getAlergenos());
            }
            return consumivelRepository.save(consumivel);
        });
    }

    public boolean delete(Long id) {
        if (consumivelRepository.existsById(id)) {
            consumivelRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
