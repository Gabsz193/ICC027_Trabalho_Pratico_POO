package br.edu.ufam.icomp.ru_digital.entities.funcionario.model;

import br.edu.ufam.icomp.ru_digital.entities.cargo.model.Cargo;
import br.edu.ufam.icomp.ru_digital.entities.usuario.model.Usuario;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "funcionario")
public class Funcionario extends Usuario {

    @ManyToMany
    @JoinTable(
        name = "funcionario_cargo",
        joinColumns = @JoinColumn(name = "funcionario_id"),
        inverseJoinColumns = @JoinColumn(name = "cargo_id")
    )
    private List<Cargo> cargos;

    public Funcionario() {
        super();
        this.cargos = new ArrayList<>();
    }

    public Funcionario(String nome, String cpf, String email, String matricula, String password) {
        super(nome, cpf, email, matricula, password);
        this.cargos = new ArrayList<>();
    }

    public List<Cargo> getCargos() {
        return cargos;
    }

    public void setCargos(List<Cargo> cargos) {
        this.cargos = cargos;
    }

    public void adicionarCargo(Cargo cargo) {
        this.cargos.add(cargo);
    }

    public void removerCargo(Cargo cargo) {
        this.cargos.remove(cargo);
    }
}