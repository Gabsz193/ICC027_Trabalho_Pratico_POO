package br.edu.ufam.icomp.ru_digital.entities.cargo.model;

import br.edu.ufam.icomp.ru_digital.entities.unidade.model.Unidade;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("ADMIN_UNIDADE")
public class AdminUnidade extends Cargo {

    public AdminUnidade() {
        super();
    }

    public AdminUnidade(Unidade unidade, Long salarioCentavos) {
        super(unidade, salarioCentavos);
    }
}
