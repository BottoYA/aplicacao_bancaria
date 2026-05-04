package com.aplicacao_bancaria.ui;

import java.util.Scanner;

import com.aplicacao_bancaria.model.Conta;
import com.aplicacao_bancaria.service.ContaService;
import com.aplicacao_bancaria.util.CSVExporter;
import com.aplicacao_bancaria.util.Validador;

public class Menu {

    private Scanner scanner = new Scanner(System.in);
    private ContaService service;

    public Menu(ContaService service) {
        this.service = service;
    }

    public void iniciar() {

        int opcao;

        do {
            System.out.println("\nBem-vindo ao Sistema Bancário! \nEscolha uma opção para prosseguir:");
            System.out.println("1 - Criar conta");
            System.out.println("2 - Login");
            System.out.println("0 - Sair");

            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    criarConta();
                    break;
                case 2:
                    realizarLogin();
                    break;
                case 0:
                    System.out.println("\nEncerrando...");
                    break;
                default:
                    System.out.println("\nOpção inválida! Tente novamente.");
            }

        } while (opcao != 0);
    }
    
    private void criarConta() {

        System.out.println("\n-- CRIAÇÃO DE CONTA --");

        String nome;
        while (true) {
            System.out.print("\nNome (ou 0 para voltar):\n");
            nome = scanner.nextLine();

            if (nome.equals("0")) return;

            if (nome.trim().isEmpty()) {
                System.out.println("Nome não pode ser vazio!");
            } else if (!Validador.validarNome(nome)) {
                System.out.println("Nome inválido! Digite pelo menos 2 letras e apenas caracteres válidos.");
            } else {
                break;
            }
        }

        String cpf;
        while (true) {
            System.out.print("\n-- Insira seu CPF:\n");
            cpf = scanner.nextLine();
            
            if (cpf.equals("0")) return;
            else if (!Validador.validarCPF(cpf)) {
                System.out.println("CPF inválido! Digite 11 números.");
            } else {
                break; // salva sem máscara
            }
        }

        System.out.print("\n-- Insira uma Senha:\n");
        String senha = scanner.nextLine();

        System.out.print("\n-- Insira o Número da conta:\n");
        String numero = scanner.nextLine();

        System.out.print("\n-- Insira a Agência:\n");
        String agencia = scanner.nextLine();

        String tipo;
        while (true) {
            System.out.print("\n-- Escolha o Tipo corrente/poupanca:\n");
            tipo = scanner.nextLine();

            if (tipo.equals("0")) return;

            else if (!Validador.validarTipoConta(tipo)) {
                System.out.println("-- Tipo inválido! Tente novamente...");
            } else {
                break;
            }
        }

        Conta conta = service.criarConta(nome, cpf, senha, numero, agencia, tipo);

        if (conta == null) {
            System.out.println("\n-- Erro: CPF já cadastrado! Tente novamente.\\n");
            criarConta();
        } else {
            System.out.println("\n-- Conta criada com sucesso!");
        }
    }
    
    private void realizarLogin() {

        System.out.println("\n-- LOGIN -- \nDigite seu CPF:\n");

        String cpf = scanner.nextLine();
        if (cpf.equals("0")) return;
        // remove tudo que não for número
        cpf = cpf.replaceAll("\\D", "");

        System.out.print("\n-- Digite sua Senha:\n");
        String senha = scanner.nextLine();

        Conta conta = service.login(cpf, senha);

        if (conta == null) {
            System.out.println("\n-- CPF ou senha inválidos! Tente novamente.\\n");
            realizarLogin(); // repete login
        } else {
            System.out.println("\n-- Login realizado com sucesso!");
            menuConta(conta);
        }
    }
    
    private void menuConta(Conta conta) {

        int opcao;

        do {
            System.out.println("\n-- LOGADO EM: " + conta.getNumeroConta() + ", BEM-VINDO " + conta.getCliente().getNome()  + "! ESCOLHA UMA OPÇÃO:");
            System.out.println("1 - Ver saldo");
            System.out.println("2 - Depositar");
            System.out.println("3 - Sacar");
            System.out.println("4 - Transferir");
            System.out.println("5 - Ver histórico");
            System.out.println("6 - Exportar CSV");
            System.out.println("0 - Logout");

            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    System.out.println("\nSaldo: R$ " + conta.getSaldo());
                    break;
                case 2:
                    depositar(conta);
                    break;
                case 3:
                    sacar(conta);
                    break;
                case 4:
                    transferir(conta);
                    break;
                case 5:
                    mostrarHistorico(conta);
                    break;
                case 6:
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

    	System.out.print("\n--- Digite o valor (ou 0 para voltar):\n");
    	double valor = scanner.nextDouble();
    	scanner.nextLine();
    	if (valor == 0) return;

        conta.depositar(valor);

        System.out.println("\n--- Depósito realizado com sucesso!");
    }
    
    private void sacar(Conta conta) {

    	System.out.print("\n--- Digite o valor (ou 0 para voltar):\n");
    	double valor = scanner.nextDouble();
    	scanner.nextLine();
    	if (valor == 0) return;

        boolean sucesso = conta.sacar(valor);

        if (!sucesso) {
            System.out.println("\n--- Saldo insuficiente! Tente novamente.\\n");
            sacar(conta); // repete operação
        } else {
            System.out.println("\n--- Saque realizado com sucesso!");
        }
    }
    
    private void transferir(Conta conta) {

        while (true) {

            System.out.print("\n--- Digite a conta de destino (ou 0 para voltar):\n");
            String destino = scanner.nextLine();

            if (destino.equals("0")) return;

            //VALIDAÇÃO ANTES DO VALOR
            Conta contaDestino = service.buscarPorNumero(destino);

            if (contaDestino == null) {
                System.out.println("\n--- Conta não encontrada!");
                continue; // volta pro início
            }

            System.out.print("\n--- Digite o valor:\n");
            double valor = scanner.nextDouble();
            scanner.nextLine(); // limpa buffer

            String resultado = service.transferir(conta.getNumeroConta(), destino, valor);

            if (!resultado.equals("OK")) {
                System.out.println("\nErro: " + resultado);
            } else {
                System.out.println("\n--- Transferência realizada com sucesso!");
                break;
            }
        }
    }
    
    private void mostrarHistorico(Conta conta) {

        System.out.println("\n=== HISTÓRICO ===");

        if (conta.getHistorico().isEmpty()) {
            System.out.println("\n--- Nenhuma transação encontrada.");
            return;
        }

        conta.getHistorico().forEach(t -> System.out.println(t));
    }
}