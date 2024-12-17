
/**
 * Write a description of class xsxs here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.Comparator;

public class ComparadorPorPrioridad implements Comparator<Paciente> {
    @Override
    public int compare(Paciente p1, Paciente p2) {
        return Integer.compare(p1.getPrioridad(), p2.getPrioridad());
    }
}
