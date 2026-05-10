package lexer;

public class Token {
    public enum TokenType {
        ID, NUM, PAREN_ABRE, PAREN_CIERRA, COMA, EOF, OPERADOR
    }
    
    public TokenType type;
    public String value;
    
    public Token(TokenType type, String value) { 
        this.type = type;
        this.value = value;
    }
}