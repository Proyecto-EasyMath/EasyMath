package main;

import evaluador.Evaluador;
import evaluador.MemoriaVariable;
import infoAyuda.InfoAyuda;
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
        System.out.println("Comandos: Escribe 'AYUDA' para ver las palabras clave disponibles, salir\n");

        while (true) {
            System.out.print("EASYMATH > ");
            String entrada = lector.nextLine().trim();

            if (entrada.equalsIgnoreCase("salir")) break;

            if (entrada.equalsIgnoreCase("ayuda")) {
                InfoAyuda.mostrarAyuda();
                continue;
            }

            if (entrada.isEmpty()) continue;

            try {
                List<Token> tokens = Lexer.tokenize(entrada);
                
                validador.validar(tokens);

                if (entrada.toUpperCase().startsWith("VALOR")) {
                    System.out.println("[SISTEMA] Variable guardada correctamente.");
                } else {
                    double res = motor.evaluar(tokens);

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