package evaluador;
import java.util.Arrays;
import java.util.List;
import lexer.Token;
import lexer.Token.TokenType;

public class Evaluador {
    private MemoriaVariable memoria;
    private List<Token> tokens;
    private int pos = 0;

    public Evaluador(MemoriaVariable memoria) {
        this.memoria = memoria;
    }

    public double evaluar(List<Token> tokens) throws Exception {
        this.tokens = tokens;
        this.pos = 0;
        return calcularExpresion();
    }

    private double calcularExpresion() throws Exception {
        Token t = tokens.get(pos);
        if (t.type == TokenType.ID) {
            if (esFuncion(t.value)) return calcularFuncion();
            pos++;
            return memoria.obtenerValor(t.value);
        } else if (t.type == TokenType.NUM) {
            pos++;
            return Double.parseDouble(t.value);
        }
        throw new Exception("Error al evaluar token: " + t.value);
    }

    private double calcularFuncion() throws Exception {
        String nombre = consume(TokenType.ID).value;
        consume(TokenType.PAREN_ABRE);

        // Primer valor base
        double acumulado = calcularExpresion();
        String opEsperado = operadorDe(nombre);

        // Bucle para infinitos argumentos
        while (tokens.get(pos).type != TokenType.PAREN_CIERRA) {
            String opActual = consume(TokenType.OPERADOR).value;
            
            // Validar que el operador sea el de la función (ej. SUMAR solo acepta +)
            if (opEsperado != null && !opActual.equals(opEsperado)) {
                throw new Exception(nombre + " no acepta el operador " + opActual + "\n"
                                    + "[SOLUCION] Cambia el operador por el necesario"
                );
            }

            double siguiente = calcularExpresion();
            acumulado = aplicarOperador(acumulado, opActual, siguiente);
        }

        consume(TokenType.PAREN_CIERRA);
        return acumulado;
    }

    private String operadorDe(String n) {
        switch(n) {
            case "SUMAR": return "+"; 
            case "RESTAR": return "-";
            case "MULTIPLICAR": return "*"; 
            case "DIVIDIR": return "/";
            case "OPERACION": return null;
            case "DIME": return null;
            case "AYUDA": return null;
            default: return null;
        }   
    }

    private double aplicarOperador(double a, String op, double b) {
        switch(op) {
            case "+": return a + b; //SUMA
            case "-": return a - b; //RESTA
            case "*": return a * b; //MULTIPLICAR
            case "/": return a / b; //DIVISION
            default: return 0;
        }
    }

    private boolean esFuncion(String n) {
    return Arrays.asList("SUMAR", "RESTAR", "MULTIPLICAR", "DIVIDIR", "PRIMERO", "DIME", "OPERACION","AYUDA").contains(n);
    }

    private Token consume(TokenType tipo) throws Exception {
        Token t = tokens.get(pos);
        if (t.type == tipo) { pos++; return t; }
        throw new Exception("Error evaluando: se esperaba " + tipo);
    }
}