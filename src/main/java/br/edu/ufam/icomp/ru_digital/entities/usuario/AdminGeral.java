package br.edu.ufam.icomp.ru_digital.entities.usuario;

import br.edu.ufam.icomp.ru_digital.entities.unidade.Unidade;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "admin_geral")
public class AdminGeral extends Usuario {

    // Construtores
    public AdminGeral() {
        super();
        this.setNivelPermissao(NivelPermissao.ALTO); // AdminGeral sempre tem alto nível
    }

    public AdminGeral(String nome, String email, String cpf, String senha) {
        super(nome, email, cpf, senha, NivelPermissao.ALTO);
    }


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

}