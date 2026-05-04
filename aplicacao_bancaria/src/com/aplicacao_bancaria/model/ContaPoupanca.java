package com.aplicacao_bancaria.model;

public class ContaPoupanca extends Conta {

    public ContaPoupanca(String numeroConta, String agencia, Cliente cliente, double saldo, double limite) {
        super(numeroConta, agencia, cliente, saldo, limite);
    }

    @Override
    public String getTipoConta() {
        return "Poupança";
    }
}