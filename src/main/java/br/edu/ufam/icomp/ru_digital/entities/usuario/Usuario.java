package br.edu.ufam.icomp.ru_digital.entities.usuario;

import jakarta.persistence.*;

@Entity
@Table(name = "usuario")
@Inheritance(strategy = InheritanceType.JOINED)
public class Usuario {
    /**
     * O ID único do usuário.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * O nome real do usuario.
     */
    @Column(nullable = false)
    private String nome;

    /**
     * O CPF válido do usuário.
     */
    @Column(unique = true, nullable = false)
    private String cpf;

    /**
     * O e-mail de contato do usuário.
     */
    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private Long saldoCentavos = 0L;

    public Usuario() {
    }

    public Usuario(String nome, String cpf, String email) {
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.saldoCentavos = 0L;
    }

    /**
     * @return O ID único do usuário.
     */
    public Long getId() { return id; }


    /**
     * Atualiza o ID do usuário.
     * @param id O novo ID único do usuário.
     */
    public void setId(Long id) { this.id = id; }

    /**
     * @return O nome real do usuário.
     */
    public String getNome() { return nome; }

    /**
     * Atualiza o nome do usuário.
     * @param nome O nome novo real do usuário.
     */
    public void setNome(String nome) { this.nome = nome; }

    /**
     * @return O CPF válido do usuário.
     */
    public String getCpf() { return cpf; }

    /**
     * Atualiza o CPF de um usuário.
     * @param cpf Um novo CPF válido.
     */
    public void setCpf(String cpf) { this.cpf = cpf; }

    /**
     * @return O e-mail de contato do usuário.
     */
    public String getEmail() { return email; }

    /**
     * Atualiza o e-mail do usuário.
     * @param email O novo e-mail do usuário.
     */
    public void setEmail(String email) { this.email = email; }

    /**
     * @return O saldo do usuário em centavos.
     */
    public Long getSaldoCentavos() { return saldoCentavos; }

    /**
     * Atualiza o saldo do usuário.
     * @param saldoCentavos O saldo do usuário, em centavos.
     */
    public void setSaldoCentavos(Long saldoCentavos) { this.saldoCentavos = saldoCentavos; }
}