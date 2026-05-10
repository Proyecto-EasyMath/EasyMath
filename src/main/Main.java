package main;

import evaluador.Evaluador;
import evaluador.MemoriaVariable;
import java.util.List;
import java.util.Scanner;
import lexer.*;
import parser.ValidadorMatematico;

public class Main {
    public static void main(String[] args) {
        Scanner lector = new Scanner(System.in);
        MemoriaVariable memoria = new MemoriaVariable();
        ValidadorMatematico validador = new ValidadorMatematico(memoria);
        Evaluador motor = new Evaluador(memoria);

        System.out.println("=== EASYMATH COMPILER v1.1 ===");
        System.out.println("Comandos: VALOR(var, num), DIME(var), SUMAR(a+b), salir\n");

        while (true) {
            System.out.print("EASYMATH > ");
            String entrada = lector.nextLine().trim();

            if (entrada.equalsIgnoreCase("salir")) break;
            if (entrada.isEmpty()) continue;

            try {
                List<Token> tokens = Lexer.tokenize(entrada);
                
                // 1. Validamos y ejecutamos asignaciones (VALOR)
                validador.validar(tokens);

                // 2. Si es una consulta o cálculo, evaluamos y mostramos
                if (entrada.toUpperCase().startsWith("VALOR")) {
                    System.out.println("[SISTEMA] Variable guardada correctamente.");
                } else {
                    double res = motor.evaluar(tokens);
                    // Si el usuario usó DIME, el resultado aparecerá aquí
                    System.out.println("[RESPUESTA] " + res);
                }

            } catch (Exception e) {
                System.err.println("[ERROR] " + e.getMessage());
            }
            System.out.println();
        }
        lector.close();
    }
}