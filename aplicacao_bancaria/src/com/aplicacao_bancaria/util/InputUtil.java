package com.aplicacao_bancaria.util;

import java.util.Scanner;

public class InputUtil {

    private Scanner scanner;

    public InputUtil(Scanner scanner) {
        this.scanner = scanner;
    }

    public int lerInt(String msg) {
        while (true) {
            try {
                System.out.print(msg + "\n");
                return Integer.parseInt(scanner.nextLine());
            } catch (Exception e) {
                System.out.println("Entrada inválida!");
            }
        }
    }

    public double lerDouble(String msg) {
        while (true) {
            try {
                System.out.print(msg + "\n");
                return Double.parseDouble(scanner.nextLine());
            } catch (Exception e) {
                System.out.println("Entrada inválida!");
            }
        }
    }

    public String lerString(String msg) {
        System.out.print(msg + "\n");
        return scanner.nextLine();
    }
}