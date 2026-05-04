package main;
import evaluador.Evaluador;
import evaluador.MemoriaVariable;
import java.util.List;
import java.util.Scanner;
import lexer.*;
import parser.ValidadorMatematico;

public class mainPRUEBA{
    
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        MemoriaVariable memoria = new MemoriaVariable();
        Evaluador evaluador = new Evaluador(memoria);
        String input = "";

        do { 
            System.out.println("Escribe la operacion que deseas realizar: ");
            input = scanner.nextLine();
            try {
                
                List<Token> tokens = Lexer.tokenize(input);
                    ValidadorMatematico validador = new ValidadorMatematico();
                    validador.validar(tokens);
                    if (!input.equals("salir")) {
                        evaluador.evaluar(tokens);
                    }
                    
                } catch (Exception e) {
                    System.out.println("ERROR: " + e.getMessage());
            }
        } while (!input.equals("salir"));

    }
}
