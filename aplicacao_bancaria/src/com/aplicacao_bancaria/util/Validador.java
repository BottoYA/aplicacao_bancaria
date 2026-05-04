package com.aplicacao_bancaria.util;

public class Validador {

	public static boolean validarNome(String nome) {
	    // remove espaços para contar apenas letras
	    String apenasLetras = nome.replaceAll("\\s+", "");

	    // verifica: só letras + espaços E pelo menos 2 letras
	    return nome.matches("[a-zA-ZÀ-ÿ ]+") && apenasLetras.length() >= 2;
	}
	
    public static boolean validarCPF(String cpf) {
        return cpf.matches("\\d{11}");
    }

    public static String formatarCPF(String cpf) {
        return cpf.replaceAll("(\\d{3})(\\d{3})(\\d{3})(\\d{2})", "$1.$2.$3-$4");
    }

    public static boolean validarTipoConta(String tipo) {
        return tipo.equalsIgnoreCase("corrente") || tipo.equalsIgnoreCase("poupanca");
    }
}