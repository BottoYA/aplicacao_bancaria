package com.aplicacao_bancaria.model;

public class Cliente {

    private String nome;
    private String cpf;
    private String senha;

    // Construtor
    public Cliente(String nome, String cpf, String senha) {
        this.nome = nome;
        this.cpf = cpf;
        this.senha = senha;
    }

    // Getters
    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }

    public String getSenha() {
        return senha;
    }
}