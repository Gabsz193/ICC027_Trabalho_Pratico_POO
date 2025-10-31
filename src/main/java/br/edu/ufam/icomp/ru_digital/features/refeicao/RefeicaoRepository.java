package br.edu.ufam.icomp.ru_digital.features.refeicao;

import br.edu.ufam.icomp.ru_digital.entities.refeicao.Refeicao;
import br.edu.ufam.icomp.ru_digital.entities.refeicao.TipoRefeicao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RefeicaoRepository extends JpaRepository<Refeicao, Long> {
    List<Refeicao> findByTipo(TipoRefeicao tipo);
    List<Refeicao> findByUnidadeId(Long unidadeId);
    List<Refeicao> findByUsuarioId(Long usuarioId);
}
