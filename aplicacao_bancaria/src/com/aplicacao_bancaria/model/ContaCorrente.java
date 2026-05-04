package com.aplicacao_bancaria.model;

public class ContaCorrente extends Conta {

    public ContaCorrente(String numeroConta, String agencia, Cliente cliente, double saldo, double limite) {
        super(numeroConta, agencia, cliente, saldo, limite);
    }

    @Override
    public String getTipoConta() {
        return "Corrente";
    }
}