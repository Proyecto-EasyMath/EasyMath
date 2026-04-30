import java.util.ArrayList;

public class mainPRUEBA{

    public static void main(String[] args) {

        //MEMORIA DE VARIABLES
        ArrayList<String> variables = new ArrayList<>();
        ArrayList<Double> valores = new ArrayList<>();
        Variable variable = new Variable();

        variable.VALOR("x", 6, variables, valores);
        variable.mostrarVariables(variables, valores);
        try {
                var tokens = Lexer.tokenize("SUMAR(3+4*5-1/2)");
                ValidadorMatematico validador = new ValidadorMatematico();
                validador.validar(tokens);
                System.out.println("Número de argumentos: " + validador.contador);
        } catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());
        }

    }


    //OPERACIONES MATEMATICAS
    public double SUMAR(double a, double b){
        double c = a + b;
        return c;
    }
    public double RESTAR(double a, double b){
        double c = a - b;
        return c;
    }
    public double DIVIDIR(double a, double b){
        double c = a / b;
        return c;
    }
    public double MULTIPLICAR(double a, double b){
        double c = a * b;
        return c;
    }
    public void variableConstante(String variable, double valor){
        System.out.println(variable + " = " + valor);
    }
    //OPERACIONES CON PRIORIDAD
    public void PRIMERO(double a, double b, String operador){
        switch (operador) {
            case "+":
                System.out.println(SUMAR(a, b));
                break;
            case "-":
                System.out.println(RESTAR(a, b));
                break;
            case "/":
                System.out.println(DIVIDIR(a, b));
                break;
            case "*":
                System.out.println(MULTIPLICAR(a, b));
                break;
            default:
                System.out.println("Operador no reconocido");
        }
    }
}
