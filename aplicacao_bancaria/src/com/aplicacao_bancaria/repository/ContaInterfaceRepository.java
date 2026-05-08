package com.aplicacao_bancaria.repository;

import com.aplicacao_bancaria.model.Conta;

public interface ContaInterfaceRepository {

    void adicionarConta(Conta conta);

    Conta buscarPorNumero(String numero);

    Conta buscarPorCpf(String cpf);
}