package com.aplicacao_bancaria.ui;

import java.util.Scanner;

import com.aplicacao_bancaria.model.Conta;
import com.aplicacao_bancaria.model.ContaCorrente;
import com.aplicacao_bancaria.model.Transacao;
import com.aplicacao_bancaria.service.ContaService;
import com.aplicacao_bancaria.util.CSVExporter;
import com.aplicacao_bancaria.util.InputUtil;
import com.aplicacao_bancaria.util.Validador;

public class Menu {

	private Scanner scanner = new Scanner(System.in);
	private InputUtil input = new InputUtil(scanner);
	private ContaService service;

	public Menu(ContaService service) {
		this.service = service;
	}

	public void iniciar() {
		while (true) {
			System.out.println("\n--- Bem-vindo ao Sistema Bancário! Escolha uma opção para prosseguir:");
			System.out.println("1 - Criar conta");
			System.out.println("2 - Login");
			System.out.println("0 - Sair");

			int opcao;

			opcao = input.lerInt("");

			switch (opcao) {
			case 1:
				criarConta();
				break;
			case 2:
				realizarLogin();
				break;
			case 0:
				System.out.println("\n--- Encerrando...");
				return;
			default:
				System.out.println("\n--- Opção inválida! Tente novamente.");
			}
		}
	}

	private void criarConta() {
		while (true) {
			System.out.println("\n-- CRIAÇÃO DE CONTA --");

			String nome;

			while (true) {
				nome = input.lerString("--- Nome (ou 0 para voltar):");

				if (nome.equals("0"))
					return;

				if (nome.trim().isEmpty()) {
					System.out.println("--- Nome não pode ser vazio!");
				} else if (!Validador.validarNome(nome)) {
					System.out.println("--- Nome inválido!");
				} else {
					break;
				}
			}

			String cpf;
			while (true) {
				cpf = input.lerString("\n-- Insira seu CPF:");

				if (cpf.equals("0"))
					return;

				if (!Validador.validarCPF(cpf)) {
					System.out.println("--- CPF inválido!");
				} else if (service.buscarPorCpf(cpf) != null) {
					System.out.println("--- CPF já cadastrado!");
				} else {
					break;
				}
			}

			String senha = input.lerString("\n-- Insira uma Senha:");

			String numero;
			while (true) {
				numero = input.lerString("\n-- Número da conta:");

				if (numero.equals("0"))
					return;

				if (!Validador.validarNumeroConta(numero)) {

					System.out.println("\n--- Número da conta inválido!");
					System.out.println("--- Digite apenas números.");

				} else if (service.buscarPorNumero(numero) != null) {
					System.out.println("\n--- Número de conta já cadastrado!");
				} else {
					break;
				}
			}

			String agencia = input.lerString("\n-- Agência:");

			String tipo;
			while (true) {
				System.out.print("\n-- Tipo (corrente/poupanca):\n");
				tipo = scanner.nextLine();

				if (tipo.equals("0"))
					return;

				if (!Validador.validarTipoConta(tipo)) {
					System.out.println("--- Tipo inválido!");
				} else {
					break;
				}
			}

			Conta conta = service.criarConta(nome, cpf, senha, numero, agencia, tipo);

			if (conta == null) {
				System.out.println("\n--- CPF já cadastrado! Tente novamente.");
			} else {
				System.out.println("\n--- Conta criada com sucesso!");
				break;
			}
		}
	}

	private void realizarLogin() {
		while (true) {
			System.out.println("\n-- LOGIN --");
			System.out.print("\nCPF (ou 0 para voltar):\n");

			String cpf;
			while (true) {
				cpf = input.lerString("\n--- CPF (apenas números ou 0 para voltar):");

				if (cpf.equals("0"))
					return;

				if (!Validador.validarCPF(cpf)) {
					System.out.println("\n--- CPF inválido! Digite 11 números.");
				} else {
					break;
				}
			}

			String senha = input.lerString("\nSenha:");

			Conta conta = service.login(cpf, senha);

			if (conta == null) {
				System.out.println("\n--- CPF ou senha inválidos! Tente novamente.");
			} else {
				System.out.println("\n--- Login realizado com sucesso!");
				menuConta(conta);
				break;
			}
		}
	}

	private void menuConta(Conta conta) {
		int opcao;
		do {
			System.out.println("\n-- LOGADO EM: " + conta.getNumeroConta() + ", BEM-VINDO " + conta.getCliente().getNome() + "! ESCOLHA UMA OPÇÃO:");
			System.out.println("1 - Ver saldo");
			System.out.println("2 - Depositar");
			System.out.println("3 - Sacar");
			System.out.println("4 - Ver limite");
			System.out.println("5 - Alterar limite");
			System.out.println("6 - Transferir");
			System.out.println("7 - Ver histórico");
			System.out.println("8 - Exportar CSV");
			System.out.println("0 - Logout");

			opcao = input.lerInt("");

			switch (opcao) {
			case 1:
				System.out.println("\n--- Saldo: R$ " + conta.getSaldo());
				break;
			case 2:
				depositar(conta);
				break;
			case 3:
				sacar(conta);
				break;
			case 4:
				verLimite(conta);
				break;
			case 5:
				alterarLimite(conta);
				break;
			case 6:
				transferir(conta);
				break;
			case 7:
				mostrarHistorico(conta);
				break;
			case 8:
				CSVExporter.exportar(conta);
				break;
			case 0:
				System.out.println("\n--- Saindo da conta...");
				break;
			default:
				System.out.println("\n--- Opção inválida!");
			}
		} while (opcao != 0);
	}

	private void depositar(Conta conta) {
		while (true) {
			double valor = input.lerDouble("\n--- Digite o valor (ou 0 para voltar):");

			if (valor == 0)
				return;

			if (valor < 0) {
				System.out.println("\n--- O valor não pode ser negativo!");
				continue;
			}

			conta.depositar(valor);
			System.out.println("\n--- Depósito realizado com sucesso!");
			break;
		}
	}

	private void sacar(Conta conta) {
		while (true) {

			double valor = input.lerDouble("\n--- Digite o valor (ou 0 para voltar):");

			if (valor == 0)
				return;

			if (valor < 0) {
				System.out.println("\n--- O valor não pode ser negativo!");
				continue;
			}

			boolean sucesso = conta.sacar(valor);

			if (!sucesso) {
				System.out.println("\n--- Saldo insuficiente!");
			} else {
				System.out.println("\n--- Saque realizado com sucesso!");
				break;
			}
		}
	}

	private void verLimite(Conta conta) {

		if (!(conta instanceof ContaCorrente)) {
			System.out.println("\n--- Esta conta não possui limite.");
		} else {
			System.out.println("\n--- Limite disponível: R$ " + conta.getLimite());

			conta.getHistorico().add(new Transacao("CONSULTA LIMITE", conta.getLimite(), "Consulta de limite realizada"));
		}
	}

	private void alterarLimite(Conta conta) {
		if (!(conta instanceof ContaCorrente)) {
			System.out.println("\n--- Esta conta não permite alteração de limite.");
			return;
		}

		while (true) {
			double novoLimite = input.lerDouble("\n--- Digite o novo limite (ou 0 para voltar):");

			if (novoLimite == 0)
				return;

			if (novoLimite < 0) {
				System.out.println("\n--- O limite não pode ser negativo!");
				continue;
			}

			boolean sucesso = conta.alterarLimite(novoLimite);

			if (!sucesso) {
				System.out.println("\n--- Limite inválido!");
			} else {
				System.out.println("\n--- Limite alterado com sucesso!");
				break;
			}
		}
	}

	private void transferir(Conta conta) {
		while (true) {
			String destino = input.lerString("\n--- Digite a conta de destino (ou 0 para voltar):");
			if (destino.equals("0"))
				return;

			Conta contaDestino = service.buscarPorNumero(destino);

			if (contaDestino == null) {
				System.out.println("\n--- Conta não encontrada!");
				continue;
			}

			double valor = input.lerDouble("\n--- Digite o valor:");

			if (valor <= 0) {
				System.out.println("\n--- Valor inválido!");
				continue;
			}

			String resultado = service.transferir(conta.getNumeroConta(), destino, valor);

			if (!resultado.equals("OK")) {
				System.out.println("\n--- Erro: " + resultado);
			} else {
				System.out.println("\n--- Transferência realizada com sucesso!");
				break;
			}
		}
	}

	private void mostrarHistorico(Conta conta) {

		System.out.println("\n--- HISTÓRICO ---");

		if (conta.getHistorico().isEmpty()) {
			System.out.println("\n--- Nenhuma transação encontrada.");
			return;
		}
		conta.getHistorico().forEach(t -> System.out.println(t));
	}
}