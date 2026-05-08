package com.aplicacao_bancaria.repository;

import java.util.ArrayList;
import java.util.List;

import com.aplicacao_bancaria.model.Conta;

public class ContaRepository implements ContaInterfaceRepository {

    private List<Conta> contas = new ArrayList<>();

    @Override
    public void adicionarConta(Conta conta) {
        contas.add(conta);
    }

    @Override
    public Conta buscarPorNumero(String numero) {
        for (Conta conta : contas) {
            if (conta.getNumeroConta().equals(numero)) {
                return conta;
            }
        }
        return null;
    }

    @Override
    public Conta buscarPorCpf(String cpf) {
        for (Conta conta : contas) {
            if (conta.getCliente().getCpf().equals(cpf)) {
                return conta;
            }
        }
        return null;
    }
}