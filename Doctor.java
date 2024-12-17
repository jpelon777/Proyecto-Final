
/**
 * Write a description of class doctor here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

public class Doctor implements Comparable<Doctor> {
    protected int id;
    protected String nombre;
    protected String especialidad;

    public Doctor(int id, String nombre, String especialidad) {
        this.id = id;
        this.nombre = nombre;
        this.especialidad = especialidad;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    @Override
    public int compareTo(Doctor otro) {
        return Integer.compare(this.id, otro.id); 
    }

    @Override
    public String toString() {
        return "Doctor{" +
               "ID=" + id +
               ", Nombre='" + nombre + '\'' +
               ", Especialidad='" + especialidad + '\'' +
               '}';
    }
}



