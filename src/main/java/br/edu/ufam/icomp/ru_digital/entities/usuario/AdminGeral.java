package br.edu.ufam.icomp.ru_digital.entities.usuario;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "admin_geral")
public class AdminGeral extends Usuario {
    /**
     * Cria um administrador geral com atributos básicos.
     * @param nome O nome real do administrador.
     * @param email O e-mail de contato do administrador.
     * @param cpf O CPF válido do administrador.
     */
    public AdminGeral(String nome, String email, String cpf) {
        super(nome, email, cpf);
    }

    protected AdminGeral() { super(); }
}