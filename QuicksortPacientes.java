import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Write a description of class quicksort here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

public class QuicksortPacientes {

    private Comparator<Paciente> comparador;

    public QuicksortPacientes(Comparator<Paciente> comparador) {
        this.comparador = comparador;
    }

    public void quicksort(ArrayList<Paciente> pacientes, int inicio, int fin) {
        if (inicio >= fin) {
            return;
        }

        int pivote = (inicio + fin) / 2; 
        Paciente pivotePaciente = pacientes.get(pivote); 

        int i = inicio;
        int d = fin;

        do {
            while (comparador.compare(pacientes.get(i), pivotePaciente) < 0) {
                i++;
            }
            while (comparador.compare(pacientes.get(d), pivotePaciente) > 0) {
                d--;
            }
            if (i <= d) {
                Collections.swap(pacientes, i, d); 
                i++;
                d--;
            }
        } while (i <= d);

        if (inicio < d) quicksort(pacientes, inicio, d);
        if (fin > i) quicksort(pacientes, i, fin);
    }
}
