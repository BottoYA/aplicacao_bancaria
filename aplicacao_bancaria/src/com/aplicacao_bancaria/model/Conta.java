package com.aplicacao_bancaria.model;

import java.util.ArrayList;
import java.util.List;

public abstract class Conta {

	protected String numeroConta;
	protected String agencia;
	protected Cliente cliente;
	protected double saldo;
	protected double limite;

	protected List<Transacao> historico = new ArrayList<>();

	public Conta(String numeroConta, String agencia, Cliente cliente, double saldo, double limite) {
		this.numeroConta = numeroConta;
		this.agencia = agencia;
		this.cliente = cliente;
		this.saldo = saldo;
		this.limite = limite;
	}

	public void depositar(double valor) {
		saldo += valor;
		historico.add(new Transacao("DEPOSITO", valor, "Deposito realizado"));
	}

	public boolean sacar(double valor) {
		if (valor <= 0)
			return false;

		if (saldo + limite >= valor) {
			saldo -= valor;
			historico.add(new Transacao("SAQUE", valor, "Saque realizado"));
			return true;
		}
		return false;
	}

	public boolean alterarLimite(double novoLimite) {
		if (novoLimite < 0)
			return false;

		this.limite = novoLimite;

		historico.add(new Transacao("ALTERACAO LIMITE", novoLimite, "Novo limite definido"));

		return true;
	}

	public abstract String getTipoConta();

	public double getSaldo() {
		return saldo;
	}

	public double getLimite() {
		return limite;
	}

	public String getNumeroConta() {
		return numeroConta;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public List<Transacao> getHistorico() {
		return historico;
	}

	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}
}