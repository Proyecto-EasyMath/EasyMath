enum TokenType{
    ID, NUM, PAREN_ABRE, PAREN_CIERRA, COMA, EOF, OPERADOR
}

class Token{
    TokenType type;
    String value;
    
    Token(TokenType type, String value) { 
        this.type = type;
        this.value = value;
    }
}