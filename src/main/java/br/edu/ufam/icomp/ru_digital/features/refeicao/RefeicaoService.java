package br.edu.ufam.icomp.ru_digital.features.refeicao;

import br.edu.ufam.icomp.ru_digital.entities.refeicao.Refeicao;
import br.edu.ufam.icomp.ru_digital.entities.refeicao.TipoRefeicao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RefeicaoService {
    private final RefeicaoRepository refeicaoRepository;

    @Autowired
    public RefeicaoService(RefeicaoRepository refeicaoRepository) {
        this.refeicaoRepository = refeicaoRepository;
    }

    public List<Refeicao> findAll() {
        return refeicaoRepository.findAll();
    }

    public Optional<Refeicao> findById(Long id) {
        return refeicaoRepository.findById(id);
    }

    public List<Refeicao> findByTipo(TipoRefeicao tipo) {
        return refeicaoRepository.findByTipo(tipo);
    }

    public List<Refeicao> findByUnidadeId(Long unidadeId) {
        return refeicaoRepository.findByUnidadeId(unidadeId);
    }

    public List<Refeicao> findByUsuarioId(Long usuarioId) {
        return refeicaoRepository.findByUsuarioId(usuarioId);
    }

    public Refeicao save(Refeicao refeicao) {
        return refeicaoRepository.save(refeicao);
    }

    public Optional<Refeicao> update(Long id, Refeicao refeicaoAtualizada) {
        return refeicaoRepository.findById(id).map(refeicao -> {
            refeicao.setNome(refeicaoAtualizada.getNome());
            refeicao.setTipo(refeicaoAtualizada.getTipo());
            refeicao.setPrecoCentavos(refeicaoAtualizada.getPrecoCentavos());
            refeicao.setDataHora(refeicaoAtualizada.getDataHora());
            refeicao.setUnidade(refeicaoAtualizada.getUnidade());
            refeicao.setUsuario(refeicaoAtualizada.getUsuario());
            if (refeicaoAtualizada.getConsumiveis() != null) {
                refeicao.setConsumiveis(refeicaoAtualizada.getConsumiveis());
            }
            return refeicaoRepository.save(refeicao);
        });
    }

    public boolean delete(Long id) {
        if (refeicaoRepository.existsById(id)) {
            refeicaoRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
