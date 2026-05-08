package com.aplicacao_bancaria.service;

import java.time.LocalTime;

import com.aplicacao_bancaria.model.*;
import com.aplicacao_bancaria.repository.ContaRepository;

public class ContaService {

	private ContaRepository repository;

	public ContaService(ContaRepository repository) {
		this.repository = repository;
	}

	public Conta criarConta(String nome, String cpf, String senha, String numero, String agencia, String tipo) {

		if (repository.buscarPorCpf(cpf) != null)
			return null;

		Cliente cliente = new Cliente(nome, cpf, senha);
		Conta conta;

		if (tipo.equalsIgnoreCase("corrente")) {
			conta = new ContaCorrente(numero, agencia, cliente, 0, 500);
		} else {
			conta = new ContaPoupanca(numero, agencia, cliente, 0, 0);
		}

		repository.adicionarConta(conta);
		return conta;
	}

	public Conta login(String cpf, String senha) {

		Conta conta = repository.buscarPorCpf(cpf);

		if (conta != null && conta.getCliente().getSenha().equals(senha)) {
			return conta;
		}

		return null;
	}

	public String transferir(String origem, String destino, double valor) {

		if (valor <= 0)
			return "Valor inválido.";

		if (origem.equals(destino))
			return "Contas iguais.";

		Conta contaOrigem = repository.buscarPorNumero(origem);
		Conta contaDestino = repository.buscarPorNumero(destino);

		if (contaOrigem == null || contaDestino == null)
			return "Conta inválida.";

		LocalTime agora = LocalTime.now();

		if (agora.isAfter(LocalTime.of(22, 0)))
			return "Transferências não permitidas após 22h.";

		if (valor > 1000 && agora.isAfter(LocalTime.of(20, 0)))
			return "Limite noturno excedido.";

		if (!contaOrigem.sacar(valor))
			return "Saldo insuficiente.";

		contaDestino.setSaldo(contaDestino.getSaldo() + valor);

		contaOrigem.getHistorico().add(new Transacao("TRANSFERÊNCIA", valor, "Enviado para " + destino));

		contaDestino.getHistorico().add(new Transacao("TRANSFERÊNCIA", valor, "Recebido de " + origem));

		return "OK";
	}

	public Conta buscarPorNumero(String numero) {
		return repository.buscarPorNumero(numero);
	}

	public Conta buscarPorCpf(String cpf) {
		return repository.buscarPorCpf(cpf);
	}
}