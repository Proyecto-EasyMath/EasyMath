import java.util.ArrayList;

public class mainGEMINI {
    // Listas para simular la "Memoria" del compilador
    ArrayList<String> nombresVar = new ArrayList<>();
    ArrayList<Double> valoresVar = new ArrayList<>();

    public static void main(String[] args) {
        mainGEMINI interprete = new mainGEMINI();

        // Simulación de ejecución según tus reglas:
        // 1. VALOR x = 10
        interprete.VALOR("x", 10.0);
        
        // 2. VALOR y = 5
        interprete.VALOR("y", 5.0);

        // 3. SUMAR(x + PRIMERO(y * 2)) -> En Java lo llamaríamos así:
        double resultado = interprete.SUMAR(
            interprete.obtenerValor("x"), 
            interprete.MULTIPLICAR(interprete.obtenerValor("y"), 2)
        );

        // 4. DIME(resultado)
        interprete.DIME("El resultado final es: " + resultado);
    }

    // REGLA: VALOR (Guardar variable)
    public void VALOR(String nombre, double valor) {
        nombresVar.add(nombre);
        valoresVar.add(valor);
    }

    // Función auxiliar para buscar variables por nombre
    public double obtenerValor(String nombre) {
        int index = nombresVar.indexOf(nombre);
        if (index != -1) return valoresVar.get(index);
        System.out.println("Error: Variable '" + nombre + "' no definida.");
        return 0;
    }

    // REGLAS MATEMÁTICAS
    public double SUMAR(double a, double b) { return a + b; }
    public double RESTAR(double a, double b) { return a - b; }
    public double MULTIPLICAR(double a, double b) { return a * b; }
    
    public double DIVIDIR(double a, double b) {
        if (b == 0) {
            System.out.println("Error: No puedes dividir entre cero.");
            return 0;
        }
        return a / b;
    }

    // REGLA: DIME (Mostrar en pantalla)
    public void DIME(Object mensaje) {
        System.out.println(mensaje);
    }
}