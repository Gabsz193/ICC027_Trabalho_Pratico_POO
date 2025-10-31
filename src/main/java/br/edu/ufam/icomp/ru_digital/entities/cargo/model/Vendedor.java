package br.edu.ufam.icomp.ru_digital.entities.cargo.model;

import br.edu.ufam.icomp.ru_digital.entities.unidade.model.Unidade;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("VENDEDOR")
public class Vendedor extends Cargo {

    public Vendedor() {
        super();
    }

    public Vendedor(Unidade unidade, Long salarioCentavos) {
        super(unidade, salarioCentavos);
    }
}
