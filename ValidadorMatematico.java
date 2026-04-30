import java.util.*;

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
        
        if (t.type == TokenType.ID) { // posible funcion
            match(TokenType.ID);
            if (lookahead().type == TokenType.PAREN_ABRE) {
                parseFunctionArgs();
            }
        } else if (t.type == TokenType.NUM) {
            match(TokenType.NUM);
        } else {
            throw new Exception("Se esperaba un identificador o número en posición " + pos);
        }
    }

    int contador = 0;
    private void parseFunctionArgs() throws Exception {
        match(TokenType.PAREN_ABRE);
        
        // Validar si la función está vacía o le falta un dato tras un operador
        if (lookahead().type == TokenType.PAREN_CIERRA) {
            throw new Exception("Faltan datos ¿no?. La función no tiene argumentos.");
        }
        while (lookahead().type != TokenType.PAREN_CIERRA) {
            parseExpression(); // Recursión para argumentos complejos
            if (lookahead().type == TokenType.OPERADOR) {
                match(TokenType.OPERADOR);
                // Si después de una operador sigue un cierre de paréntesis, falta un dato
                if (lookahead().type == TokenType.PAREN_CIERRA) {
                    throw new Exception("¡CASI! Se esperaba un numero/variable después del operador [+, -, /, *]");
                }
            } else if (lookahead().type != TokenType.PAREN_CIERRA) {
                throw new Exception("Se esperaba un operador [+, -, *, /] o el parentesis de cierre [')']");
            }
        }
        match(TokenType.PAREN_CIERRA);
        System.out.println("¡Sintaxis valido!");
    }

    private Token lookahead() {
        if (pos < tokens.size()) return tokens.get(pos);
        return new Token(TokenType.EOF, "");
    }

    private void match(TokenType type) throws Exception {
        if (lookahead().type == type) pos++;
        else throw new Exception("Error: Se esperaba " + type + " pero se encontró " + lookahead().type);
    }
}