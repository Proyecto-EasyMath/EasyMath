package infoAyuda;

import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

public class InfoAyuda {
    public static void mostrarAyuda() {
        try {
            System.setOut(new PrintStream(System.out, true, StandardCharsets.UTF_8));
        } catch (Exception e) {
            
        }
        System.out.println("\n=== COMANDOS DISPONIBLES ===\n");
        
        System.out.println("SUMAR \u2192 Permite hacer una suma de varios argumentos");
        System.out.println("SUMAR(10 + 5 + 3...)\n");
        
        System.out.println("RESTAR → Permite hacer una resta de varios argumentos");
        System.out.println("RESTAR(20 - 5 - 3...)\n");
        
        System.out.println("MULTIPLICAR → Permite hacer una multiplicacion de varios argumentos");
        System.out.println("MULTIPLICAR(4 * 3 * 2...)\n");
        
        System.out.println("DIVIDIR → Permite hacer una division entre dos argumentos");
        System.out.println("DIVIDIR(100 / 5 / 2...)\n");
        
        System.out.println("PRIMERO → Indica que una operacion se hace primero dentro de otras");
        System.out.println("PRIMERO(MULTIPLICAR(2 * 3) + 4...)\n");
        
        System.out.println("OPERACION → Deja libre el uso de operadores para hacer operaciones mas complejas");
        System.out.println("OPERACION(5 + 3 * 2 - 1...)\n");
        
        System.out.println("VALOR → Asigna un valor a una variable");
        System.out.println("VALOR(x, 42)\n");
        
        System.out.println("DIME → Muestra el valor asociado a una variable");
        System.out.println("DIME(x)\n");
        
        System.out.println("AYUDA → Muestra este menu de ayuda\n");
    } 
}
