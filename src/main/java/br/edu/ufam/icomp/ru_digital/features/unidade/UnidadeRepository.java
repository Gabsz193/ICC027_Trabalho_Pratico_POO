package br.edu.ufam.icomp.ru_digital.features.unidade;

import br.edu.ufam.icomp.ru_digital.entities.unidade.model.Unidade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UnidadeRepository extends JpaRepository<Unidade, Long> {
    Optional<Unidade> findByNome(String nome);
    List<Unidade> findByNomeContainingIgnoreCase(String nome);
}
