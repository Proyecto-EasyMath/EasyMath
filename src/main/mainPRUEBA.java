package main;
import java.util.List;
import java.util.Scanner;
import lexer.*;
import parser.ValidadorMatematico;

public class mainPRUEBA{
    
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        String input = "";

        do { 
            try {
                System.out.println("Escribe la operacion que deseas realizar: ");
                input = scanner.nextLine();
                
                List<Token> tokens = Lexer.tokenize(input);
                    ValidadorMatematico validador = new ValidadorMatematico();
                    validador.validar(tokens);
                    
                } catch (Exception e) {
                    System.out.println("ERROR: " + e.getMessage());
            }
        } while (input != "salir");

    }
}
