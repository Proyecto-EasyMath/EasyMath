package lexer;

public class Token {
    // 1. El enum DEBE ser público para que el Validador pueda comparar: 
    //    if (t.type == Token.TokenType.ID)
    public enum TokenType {
        ID, NUM, PAREN_ABRE, PAREN_CIERRA, COMA, EOF, OPERADOR
    }
    
    public TokenType type;
    public String value;
    
    // 2. El constructor DEBE ser público para que el Lexer pueda crear tokens
    public Token(TokenType type, String value) { 
        this.type = type;
        this.value = value;
    }
}