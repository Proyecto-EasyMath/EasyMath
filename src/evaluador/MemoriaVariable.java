package evaluador;

import java.util.HashMap;
import java.util.Map;

public class MemoriaVariable {
    // La memoria ahora es un solo mapa interno
    private HashMap<String, Double> memoria = new HashMap<>();

    // MUESTRA LAS VARIABLES Y SUS VALORES
    public void mostrarVariables() {
        if (memoria.isEmpty()) {
            System.out.println("La memoria está vacía.");
            return;
        }
        for (Map.Entry<String, Double> entrada : memoria.entrySet()) {
            System.out.println(entrada.getKey() + " = " + entrada.getValue());
        }
    }

    // AGREGA O ACTUALIZA UNA VARIABLE (Tu método VALOR y valorVariable unidos)
    public void asignar(String nombre, double valor) {
        memoria.put(nombre, valor); // put agrega si no existe, o actualiza si ya existe
    }

    // ELIMINA UNA VARIABLE DE LA MEMORIA
    public void eliminarVariable(String nombre) {
        if (memoria.containsKey(nombre)) {
            memoria.remove(nombre);
        } else {
            System.out.println("Error: Variable '" + nombre + "' no encontrada.");
        }
    }

    // OBTIENE EL VALOR (Muy útil para el evaluador matemático)
    public double obtenerValor(String nombre) throws Exception {
        if (memoria.containsKey(nombre)) {
            return memoria.get(nombre);
        }
        throw new Exception("Variable '" + nombre + "' no definida.");
    }
}