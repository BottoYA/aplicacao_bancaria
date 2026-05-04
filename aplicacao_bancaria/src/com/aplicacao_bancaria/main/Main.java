package com.aplicacao_bancaria.main;

import com.aplicacao_bancaria.repository.ContaRepository;
import com.aplicacao_bancaria.service.ContaService;
import com.aplicacao_bancaria.ui.Menu;

public class Main {

    public static void main(String[] args) {

        ContaRepository repository = new ContaRepository();
        ContaService service = new ContaService(repository);
        Menu menu = new Menu(service);

        menu.iniciar();
    }
}