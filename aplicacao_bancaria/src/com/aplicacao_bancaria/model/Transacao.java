package com.aplicacao_bancaria.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Transacao {

    private String tipo;
    private double valor;
    private LocalDateTime data;
    private String descricao;

    public Transacao(String tipo, double valor, String descricao) {
        this.tipo = tipo;
        this.valor = valor;
        this.data = LocalDateTime.now();
        this.descricao = descricao;
    }

    public String toCSV() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        return data.format(formatter) + ";" + tipo + ";" + valor + ";" + descricao;
    }

    @Override
    public String toString() {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

        return data.format(formatter) + " - " + tipo + " - R$ " + valor + " - " + descricao;
    }
}