package evaluador;
import java.util.HashMap;

public class MemoriaVariable {
    private HashMap<String, Double> memoria = new HashMap<>();

    public void asignar(String nombre, double valor) {
        memoria.put(nombre, valor);
    }

    public double obtenerValor(String nombre) throws Exception {
        if (memoria.containsKey(nombre)) return memoria.get(nombre);
        throw new Exception("La variable '" + nombre + "' no existe." + "\n"
                            + "[SOLUCION] Crea la variable con VALOR(" + nombre + "," + "valor)" + "\n"
        );
    }

    public void mostrarVariables() {
        System.out.println("--- MEMORIA ACTUAL ---");
        memoria.forEach((k, v) -> System.out.println(k + " = " + v));
    }
}