
/**
 * Write a description of class cita here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

public class Cita {
    Paciente paciente;
    Doctor doctor;
    Area area;

    public Cita(Paciente paciente, Doctor doctor, Area area) {
        this.paciente = paciente;
        this.doctor = doctor;
        this.area = area;
    }

    @Override
    public String toString() {
        return "Cita [Paciente: " + paciente.nombre + ", Doctor: " + doctor.nombre + 
               ", Área: " + area.nombre + "]";
    }
}

