package br.edu.ufam.icomp.ru_digital.entities.usuario;

import br.edu.ufam.icomp.ru_digital.entities.usuario.model.Usuario;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "admin_geral")
public class AdminGeral extends Usuario {

    // Construtores
    public AdminGeral() {
        super();
    }

    public AdminGeral(String nome, String email, String cpf, String matricula, String password) {
        super(nome, email, cpf, matricula, password);
    }

    /*
    // Métodos de negócio
    public void autorizarAdminUnidade(Usuario admin) {
        if (admin == null) {
            throw new IllegalArgumentException("Usuário inválido para autorização.");
        }
        admin.setNivelPermissao(NivelPermissao.MEDIO);
    }

    public void registrarUnidade(Unidade unidade) {
        if (unidade == null) {
            throw new IllegalArgumentException("Unidade inválida para registro.");
        }
        // Aqui normalmente chamaríamos um serviço ou repositório
        System.out.println("Unidade registrada: " + unidade.getNome());
    }

    public void removerUnidade(Unidade unidade) {
        if (unidade == null) {
            throw new IllegalArgumentException("Unidade inválida para remoção.");
        }
        // Aqui normalmente chamaríamos um serviço ou repositório
        System.out.println("Unidade removida: " + unidade.getNome());
    }
     */
}