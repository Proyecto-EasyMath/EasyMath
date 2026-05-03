package parser;
import java.util.*;
import lexer.*;
import lexer.Token.TokenType;

public class ValidadorMatematico {
    private List<Token> tokens;
    private int pos = 0;

    public void validar(List<Token> tokens) throws Exception {
        this.tokens = tokens;
        this.pos = 0;
        parseExpression();
        if (pos < tokens.size() - 1) throw new Exception("Error: Contenido inesperado al final.");
    }

    private void parseExpression() throws Exception {
        Token t = tokens.get(pos);
        
        if (t.type == TokenType.ID) { // Identifica una funcion
            validarID();
        } else if (t.type == TokenType.NUM) { // De no ser una funcion, salta a ser una varible y lee su valor
            match(TokenType.NUM);
        } else {
            throw new Exception("Se esperaba un identificador o número en la posicion " + pos);
        }

    }
    private void parseFunctionArgs() throws Exception {
        match(TokenType.PAREN_ABRE);
        
        // Validar si la función está vacía o le falta un dato tras un operador
        if (lookahead().type == TokenType.PAREN_CIERRA) {
            throw new Exception("Faltan datos: La función no contiene numeros ni operadores con los que trabajar");
        }
        while (lookahead().type != TokenType.PAREN_CIERRA) {
            parseExpression(); // Recursión para argumentos complejos
            if (lookahead().type == TokenType.OPERADOR) {
                match(TokenType.OPERADOR);
                // Si después de una operador sigue un cierre de paréntesis, falta un dato
                if (lookahead().type == TokenType.PAREN_CIERRA) {
                    throw new Exception("¡CASI! Se esperaba un número/variable después del operador [+, -, /, *] en la posicion: " + pos);
                }
            } else if (lookahead().type != TokenType.PAREN_CIERRA) {
                throw new Exception("Se esperaba un operador [+, -, *, /] o el parentesis de cierre [')']");
            }
        }
        match(TokenType.PAREN_CIERRA);
        System.out.println("¡Sintaxis valido!");
    }

    // OBTIENE QUE TOKEN ES EL SIGUIENTE A EVALUAR SIN MOVERSE DEL ACTUAL
    private Token lookahead() {
        if (pos < tokens.size()) return tokens.get(pos);
        return new Token(TokenType.EOF, "");
    }

    // VERIFICA EL TIPO DE TOKEN
    private void match(TokenType type) throws Exception {
        if (lookahead().type == type) pos++;
        else throw new Exception("Error: Se esperaba un parentesis de apertura en posicion: " + pos);
    } 

    // VALIDA SI ES UNA OPERACION O UNA VARIBLE
    private void validarID() throws Exception {
        Token t = tokens.get(pos);
        String nombre = t.value;

        if(nombre.equals("salir")){
        } else {
            if(esFuncion(nombre)){
                System.out.println("Es una operacion: " + nombre);
                match(TokenType.ID);
                parseFunctionArgs();
            } else{
                System.out.println("Es una variable: " + nombre);
                match(TokenType.ID);
            }
        }
    }
    
    // LISTA DE PALABRAS PARA IDENTIFICAR TOKEN DE FUNCIONES
    private boolean esFuncion(String nombre){
        return nombre.equals("PRIMERO") || nombre.equals("SUMAR") || nombre.equals("RESTAR") || nombre.equals("MULTIPLICAR") || nombre.equals("DIVIDIR");
    }
}