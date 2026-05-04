package com.aplicacao_bancaria.util;

import java.io.FileWriter;

import com.aplicacao_bancaria.model.Conta;
import com.aplicacao_bancaria.model.Transacao;

public class CSVExporter {

    public static void exportar(Conta conta) {

        String caminho = System.getProperty("user.home") + "/Downloads/historico_" 
                        + conta.getNumeroConta() + ".csv";

        try (FileWriter writer = new FileWriter(caminho)) {

        	writer.write("Data;Tipo;Valor;Descricao\n");

            for (Transacao t : conta.getHistorico()) {
                writer.write(t.toCSV() + "\n");
            }

            System.out.println("\nExportado para: " + caminho);

        } catch (Exception e) {
            System.out.println("Erro ao exportar CSV.");
        }
    }
}