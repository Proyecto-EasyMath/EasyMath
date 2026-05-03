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


    //OPERACIONES MATEMATICAS
    public double SUMAR(double a, double b){
        double c = a + b;
        return c;
    }
    public double RESTAR(double a, double b){
        double c = a - b;
        return c;
    }
    public double DIVIDIR(double a, double b){
        double c = a / b;
        return c;
    }
    public double MULTIPLICAR(double a, double b){
        double c = a * b;
        return c;
    }
    public void variableConstante(String variable, double valor){
        System.out.println(variable + " = " + valor);
    }
    //OPERACIONES CON PRIORIDAD
    public void PRIMERO(double a, double b, String operador){
        switch (operador) {
            case "+":
                System.out.println(SUMAR(a, b));
                break;
            case "-":
                System.out.println(RESTAR(a, b));
                break;
            case "/":
                System.out.println(DIVIDIR(a, b));
                break;
            case "*":
                System.out.println(MULTIPLICAR(a, b));
                break;
            default:
                System.out.println("Operador no reconocido");
        }
    }
}
