import java.util.*;
import java.util.regex.*;

public class Lexer {
    public static List<Token> tokenize(String input) throws Exception {
        List<Token> tokens = new ArrayList<>();
        
        Pattern pattern = Pattern.compile("(?<NUM>\\d+(\\.\\d+)?)|(?<ID>[a-zA-Z_][a-zA-Z0-9_]*)|(?<OP>[\\+\\-\\*\\/])|(?<PARENL>\\()|(?<PARENR>\\))|(?<COMMA>,)|(?<SKIP>\\s+)");
        Matcher matcher = pattern.matcher(input);

        while (matcher.find()) {
            if (matcher.group("NUM") != null) {
                tokens.add(new Token(TokenType.NUM, matcher.group("NUM")));
            } else if (matcher.group("ID") != null) {
                tokens.add(new Token(TokenType.ID, matcher.group("ID")));
            } else if (matcher.group("PARENL") != null) {
                tokens.add(new Token(TokenType.PAREN_ABRE, "("));
            } else if (matcher.group("PARENR") != null) {
                tokens.add(new Token(TokenType.PAREN_CIERRA, ")"));
            } else if (matcher.group("COMMA") != null) {
                tokens.add(new Token(TokenType.COMA, ","));
            }else if (matcher.group("OP") != null) { // Identifica que tipo Operador es
                if(matcher.group("OP").contains("+")){
                    tokens.add(new Token(TokenType.OPERADOR, "+"));
                } else if (matcher.group("OP").contains("-")){
                    tokens.add(new Token(TokenType.OPERADOR, "-"));
                } else if (matcher.group("OP").contains("*")){
                    tokens.add(new Token(TokenType.OPERADOR, "*"));
                } else if (matcher.group("OP").contains("/")){
                    tokens.add(new Token(TokenType.OPERADOR, "/"));
                }
            } else if (matcher.group("SKIP") != null) {
                continue; // Ignoramos los espacios
            } else {
                throw new Exception("Carácter no reconocido: " + matcher.group());
            }
        }
        
        tokens.add(new Token(TokenType.EOF, "")); // Marcamos el final
        return tokens;
    }
}