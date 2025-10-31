package br.edu.ufam.icomp.ru_digital.features.consumivel;

import br.edu.ufam.icomp.ru_digital.entities.consumivel.Consumivel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConsumivelRepository extends JpaRepository<Consumivel, Long> {
    List<Consumivel> findByNomeContainingIgnoreCase(String nome);
}
