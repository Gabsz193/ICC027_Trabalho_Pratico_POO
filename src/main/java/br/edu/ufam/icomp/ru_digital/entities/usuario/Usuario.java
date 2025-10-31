package br.edu.ufam.icomp.ru_digital.entities.usuario;

import jakarta.persistence.*;

import java.util.Arrays;
import java.util.stream.IntStream;

public class Usuario {
    private Long id;
    private String nome;
    private String email;
    private String cpf;
    private String senha;
    private NivelPermissao nivelPermissao = NivelPermissao.BAIXO;

    // Enum interna ou pode ser movida para um arquivo separado
    public enum NivelPermissao {
        BAIXO,
        MEDIO,
        ALTO
    }

    // Construtores
    public Usuario() {}

    public Usuario(String nome, String email, String cpf, String senha, NivelPermissao nivelPermissao) {
        setNome(nome);
        setEmail(email);
        setCpf(cpf);
        setSenha(senha);
        setNivelPermissao(nivelPermissao);
    }

    // Getters e Setters (Encapsulamento)
    public Long getId() {
        return id;
    }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getCpf()  { return cpf; }
    public void setCpf(String cpf) {
        if (cpf == null)
            throw new IllegalArgumentException("O CPF não pode ser nulo.");

        // Filtrar apenas números
        int[] digitos = cpf.chars()
                .filter(Character::isDigit)
                .map(x -> Character.digit(x, 10))
                .toArray();

        if (digitos.length != 11)
            throw new IllegalArgumentException("O CPF é inválido");

        // Calcular dígitos verificadores
        int[] verif = { 0, 0 };
        for (int i = 0; i < 9; i++) {
            verif[0] += (10 - i) * digitos[i];
            verif[1] += (11 - i) * digitos[i];
        }
        verif[1] += 2 * digitos[9];

        // Verificar dígitos finais
        for (int j = 0; j < 2; j++) {
            int resto = verif[j] % 11;
            verif[j] = (resto < 2) ? (0) : (11 - resto);

            if (digitos[9 + j] != verif[j])
                throw new IllegalArgumentException("O CPF é inválido");
        }

        this.cpf = cpf;
    }

    public String getEmail() { return email; }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public NivelPermissao getNivelPermissao() {
        return nivelPermissao;
    }

    public void setNivelPermissao(NivelPermissao nivelPermissao) {
        this.nivelPermissao = nivelPermissao;
    }
}