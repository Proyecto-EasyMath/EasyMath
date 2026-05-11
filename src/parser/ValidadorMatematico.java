package parser;
import evaluador.MemoriaVariable;
import java.util.*;
import lexer.*;
import lexer.Token.TokenType;

public class ValidadorMatematico {
    private List<Token> tokens;
    private int pos = 0;
    private MemoriaVariable memoria;

    public ValidadorMatematico(MemoriaVariable memoria) {
        this.memoria = memoria;
    }

    public void validar(List<Token> tokens) throws Exception {
        this.tokens = tokens;
        this.pos = 0;
        if (tokens.isEmpty()) return;
        parseExpression();
    }

    private void parseExpression() throws Exception {
        Token t = lookahead();
        if (t.type == TokenType.ID) {
            validarID();
        } else if (t.type == TokenType.NUM) {
            match(TokenType.NUM);
        } else {
            throw new Exception("Se esperaba número o identificador en pos " + pos);
        }
    }

    private void validarID() throws Exception {
        Token t = tokens.get(pos);
        String nombre = t.value;

        if (nombre.equalsIgnoreCase("AYUDA")) {
            match(TokenType.ID);
        }
        if (nombre.equals("VALOR")) {
            match(TokenType.ID);
            match(TokenType.PAREN_ABRE);
            String varNombre = match(TokenType.ID).value;
            match(TokenType.COMA); // Asegúrate de tener COMA en tu Lexer
            double varValor = Double.parseDouble(match(TokenType.NUM).value);
            match(TokenType.PAREN_CIERRA);
            memoria.asignar(varNombre, varValor);
        } else if (esFuncion(nombre)) {
            match(TokenType.ID);
            parseFunctionArgs();
        } else {
            memoria.obtenerValor(nombre); // Verifica si existe
            match(TokenType.ID);
        }
    }

    private void parseFunctionArgs() throws Exception {
        match(TokenType.PAREN_ABRE);
        while (lookahead().type != TokenType.PAREN_CIERRA) {
            parseExpression();
            if (lookahead().type == TokenType.OPERADOR) {
                match(TokenType.OPERADOR);
            }
        }
        match(TokenType.PAREN_CIERRA);
    }

    private boolean esFuncion(String n) {
    return Arrays.asList("SUMAR", "RESTAR", "MULTIPLICAR", "DIVIDIR", "PRIMERO", "DIME", "OPERACION","AYUDA").contains(n);
    }

    private Token lookahead() {
        return (pos < tokens.size()) ? tokens.get(pos) : new Token(TokenType.EOF, "");
    }

    private Token match(TokenType tipo) throws Exception {
        Token t = lookahead();
        if (t.type == tipo) { pos++; return t; }
        throw new Exception("Se esperaba " + tipo + " pero llegó " + t.type);
    }
}