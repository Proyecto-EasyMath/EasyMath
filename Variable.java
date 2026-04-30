import java.util.ArrayList;

public class Variable{

//MUESTRA LAS VARIABLES Y SUS VALORES
    public void mostrarVariables(ArrayList<String> variables, ArrayList<Double> valores) {
        for (int i = 0; i < variables.size(); i++) {
            System.out.println(variables.get(i) + " = " + valores.get(i));
        }
    }
    //AGREGA UNA VARIABLE A LA MEMORIA
    public void VALOR(String nombre, double valor, ArrayList<String> variables, ArrayList<Double> valores) {
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
}

