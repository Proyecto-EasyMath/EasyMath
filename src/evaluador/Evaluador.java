package evaluador;

import java.util.List;
import lexer.Token;
import lexer.Token.TokenType;

public class Evaluador {

    private List<Token> tokens;
    private int pos = 0;
    private MemoriaVariable memoria;

    public Evaluador(MemoriaVariable memoria) {
        this.memoria = memoria;
    }

    // PUNTO DE ENTRADA: recibe los tokens ya validados y calcula el resultado
    public void evaluar(List<Token> tokens) throws Exception {
        this.tokens = tokens;
        this.pos = 0;
        double resultado = calcularExpresion();
        System.out.println("Resultado: " + resultado);
    }

    // DECIDE QUÉ CALCULAR: si es función la evalúa, si es número lo devuelve
    private double calcularExpresion() throws Exception {
        Token t = tokens.get(pos);

        if (t.type == TokenType.ID) {
            return calcularFuncion();
        } else if (t.type == TokenType.NUM) {
            pos++;
            return Double.parseDouble(t.value);
        }

        throw new Exception("Token inesperado: " + t.value);
    }

    // EVALÚA LA FUNCIÓN SEGÚN SU NOMBRE Y CALCULA SU RESULTADO
    private double calcularFuncion() throws Exception {
        String nombre = consume(TokenType.ID).value;
        consume(TokenType.PAREN_ABRE);

        double a = calcularExpresion();

        // *** CAMBIO: verificamos primero qué símbolo espera esta función
        //     para dar un mensaje de error específico en vez de "se esperaba OPERADOR" ***
        String operadorEsperado = operadorDe(nombre);
        if (tokens.get(pos).type != TokenType.OPERADOR)
            throw new Exception("'" + nombre + "' requiere '" + operadorEsperado
                + "' entre los dos argumentos.\n"
                + "EJEMPLO: " + nombre + "(a " + operadorEsperado + " b)");

        String operador = consume(TokenType.OPERADOR).value;

        if (operadorEsperado != null && !operador.equals(operadorEsperado))
            throw new Exception("'" + nombre + "' debe usar '" + operadorEsperado
                + "' como operador, no '" + operador + "'.\n"
                + "EJEMPLO: " + nombre + "(a " + operadorEsperado + " b)");

        double b = calcularExpresion();

        consume(TokenType.PAREN_CIERRA);

        double resultado;
        switch (nombre) {
            case "SUMAR":
                resultado = a + b;
                System.out.println("SUMAR(" + a + " + " + b + ") = " + resultado);
                return resultado;
            case "RESTAR":
                resultado = a - b;
                System.out.println("RESTAR(" + a + " - " + b + ") = " + resultado);
                return resultado;
            case "MULTIPLICAR":
                resultado = a * b;
                System.out.println("MULTIPLICAR(" + a + " * " + b + ") = " + resultado);
                return resultado;
            case "DIVIDIR":
                resultado = a / b;
                System.out.println("DIVIDIR(" + a + " / " + b + ") = " + resultado);
                return resultado;
            case "PRIMERO":
                resultado = aplicarOperador(a, operador, b);
                System.out.println("PRIMERO(" + a + " " + operador + " " + b + ") = " + resultado);
                return resultado;
            default:
                throw new Exception("Función desconocida: " + nombre);
        }
    }

    // DEVUELVE EL OPERADOR QUE LE CORRESPONDE A CADA FUNCIÓN
    // PRIMERO devuelve null porque acepta cualquier operador
    private String operadorDe(String nombre) {
        switch (nombre) {
            case "SUMAR":       return "+";
            case "RESTAR":      return "-";
            case "MULTIPLICAR": return "*";
            case "DIVIDIR":     return "/";
            default:            return null;
        }
    }

    // APLICA EL OPERADOR ENTRE DOS NÚMEROS
    private double aplicarOperador(double a, String op, double b) throws Exception {
        switch (op) {
            case "+": return a + b;
            case "-": return a - b;
            case "*": return a * b;
            case "/": return a / b;
            default:  throw new Exception("Operador desconocido: " + op);
        }
    }

    // AVANZA Y VERIFICA
    private Token consume(TokenType esperado) throws Exception {
        Token t = tokens.get(pos);
        if (t.type != esperado)
            throw new Exception("Se esperaba " + esperado
                + " pero se encontró '" + t.value + "' en posición " + pos);
        pos++;
        return t;
    }
}