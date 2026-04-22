import java.util.ArrayList;

public class mainPRUEBA{

    public static void main(String[] args) {

        //MEMORIA DE VARIABLES
        ArrayList<String> variables = new ArrayList<>();
        ArrayList<Double> valores = new ArrayList<>();
        
        mainPRUEBA instance = new mainPRUEBA();

        instance.addVariable("x", 2.0, variables, valores);

        instance.mostrarVariables(variables, valores);
    }

    //MUESTRA LAS VARIABLES Y SUS VALORES
    public void mostrarVariables(ArrayList<String> variables, ArrayList<Double> valores) {
        for (int i = 0; i < variables.size(); i++) {
            System.out.println(variables.get(i) + " = " + valores.get(i));
        }
    }
    //AGREGA UNA VARIABLE A LA MEMORIA
    public void addVariable(String nombre, double valor, ArrayList<String> variables, ArrayList<Double> valores) {
        variables.add(nombre);
        valores.add(valor);
    }

    //ELIMINA UNA VARIABLE DE LA MEMORIA
    public void eliminarVariable(String nombre, ArrayList<String> variables, ArrayList<Double> valores) {
        int index = variables.indexOf(nombre);
        if (index != -1) {
            variables.remove(index);
            valores.remove(index);
        } else {
            System.out.println("Variable no encontrada");
        }
    }

    //ENCUENTRA EL VALOR DE UNA VARIABLE
    public void valorVariable(String nombre, double valor, ArrayList<String> variables, ArrayList<Double> valores) {
        int index = variables.indexOf(nombre);
        if (index != -1) {
            valores.set(index, valor);
        } else {
            System.out.println("Variable no encontrada");
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
