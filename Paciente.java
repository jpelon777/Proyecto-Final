/**
 * Write a description of class paciente here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

public class Paciente implements Comparable<Paciente> {
    protected int id;
    protected String nombre;
    protected int edad;
    protected String diagnostico;
    protected int prioridad;

    public Paciente(int id, String nombre, int edad, String diagnostico, int prioridad) {
        this.id = id;
        this.nombre = nombre;
        this.edad = edad;
        this.diagnostico = diagnostico;
        this.prioridad = prioridad;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public int getEdad() {
        return edad;
    }

    public String getDiagnostico() {
        return diagnostico;
    }

    public int getPrioridad() {
        return prioridad;
    }

    @Override
    public int compareTo(Paciente otro) {
        return Integer.compare(this.id, otro.id); 
    }

    @Override
    public String toString() {
        return "Paciente{" +
               "ID=" + id +
               ", Nombre='" + nombre + '\'' +
               ", Edad=" + edad +
               ", Diagnostico='" + diagnostico + '\'' +
               ", Prioridad=" + prioridad +
               '}';
    }
}
