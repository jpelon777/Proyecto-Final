
/**
 * Write a description of class SistemaHospital here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class SistemaHospital {

    private static ArbolAVL<Paciente> arbolPacientes = new ArbolAVL<>();
    private static ArbolAVL<Doctor> arbolDoctores = new ArbolAVL<>();
    private static Grafo grafoAreas = new Grafo();
    private static ColaDinamica<Cita> colaCitas = new ColaDinamica<>();
    private static PilaDinamica<Cita> pilaCitas = new PilaDinamica<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        LectorCSV.leerPacientes("pacientes.csv", arbolPacientes);
        LectorCSV.leerDoctores("doctores.csv", arbolDoctores);
        LectorCSV.leerAreasConConexiones("areas.csv", grafoAreas);

        int opcion;
        do {
            System.out.println("\n=== Sistema de Gestión de Hospitales ===");
            System.out.println("1. Mostrar pacientes");
            System.out.println("2. Mostrar doctores");
            System.out.println("3. Mostrar áreas y conexiones");
            System.out.println("4. Buscar paciente por ID");
            System.out.println("5. Buscar doctor por ID");
            System.out.println("6. Agendar cita");
            System.out.println("7. Mostrar citas agendadas");
            System.out.println("8. Marcar citas como atendidas");
            System.out.println("9. Mostrar citas atendidas");
            System.out.println("10. Mostrar pacientes en estado critico");
            System.out.println("11. Salir");
            System.out.print("Elige una opción: ");
            opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {
                case 1:
                    arbolPacientes.obtenerDatos().forEach(System.out::println);
                    break;
                case 2:
                    arbolDoctores.obtenerDatos().forEach(System.out::println);
                    break;
                case 3:
                    System.out.println(grafoAreas);
                    break;
                case 4:
                    buscarPaciente(sc);
                    break;
                case 5:
                    buscarDoctor(sc);
                    break;
                case 6:
                    agendarCita(sc);
                    break;
                case 7:
                    mostrarCitas(colaCitas);
                    break;
                case 8:
                    marcarCitaComoAtendida(sc);
                    break;
                case 9:
                    mostrarCitasAtendidas(pilaCitas);
                    break;
                case 10:
                    ordenarPacientesPorPrioridad();
                    break;
                case 11:
                    System.out.println("Saliendo del sistema...");
                    break;
                default:
                    System.out.println("Opción inválida. Inténtalo de nuevo.");
            }
        } while (opcion != 11);
    }

    private static void buscarPaciente(Scanner sc) {
        System.out.print("ID del paciente: ");
        int id = sc.nextInt();
        Paciente p = arbolPacientes.buscar(new Paciente(id, "", 0, "", 0));
        System.out.println(p != null ? p : "Paciente no encontrado.");
    }

    private static void buscarDoctor(Scanner sc) {
        System.out.print("ID del doctor: ");
        int id = sc.nextInt();
        Doctor d = arbolDoctores.buscar(new Doctor(id, "", ""));
        System.out.println(d != null ? d : "Doctor no encontrado.");
    }

    private static void agendarCita(Scanner sc) {
        System.out.print("ID del paciente: ");
        int idPaciente = sc.nextInt();
        Paciente paciente = arbolPacientes.buscar(new Paciente(idPaciente, "", 0, "", 0));

        System.out.print("ID del doctor: ");
        int idDoctor = sc.nextInt();
        Doctor doctor = arbolDoctores.buscar(new Doctor(idDoctor, "", ""));

        System.out.print("Nombre del área: ");
        sc.nextLine();
        String nombreArea = sc.nextLine();

        if (paciente != null && doctor != null && grafoAreas.existeVertice(nombreArea)) {
            Cita cita = new Cita(paciente, doctor, new Area(nombreArea));
            colaCitas.agregar(cita);  
            System.out.println("Cita agendada: " + cita);
        } else {
            System.out.println("Error: Verifica los datos.");
        }
    }

    private static void mostrarCitas(ColaDinamica<Cita> cola) {
        if (cola.estaVacia()) {
            System.out.println("No hay citas agendadas.");
        } else {
            try {
                System.out.println("Citas agendadas:");
                ColaDinamica<Cita> colaTemporal = new ColaDinamica<>();
                
                while (!cola.estaVacia()) {
                    Cita cita = cola.eliminar();
                    System.out.println(cita);
                    colaTemporal.agregar(cita); 
                }
                
                while (!colaTemporal.estaVacia()) {
                    cola.agregar(colaTemporal.eliminar());
                }
            } catch (Exception e) {
                System.out.println("Error al mostrar citas: " + e.getMessage());
            }
        }
    }

    
    private static void marcarCitaComoAtendida(Scanner sc) {
        if (colaCitas.estaVacia()) {
            System.out.println("No hay citas agendadas.");
            return;
        }
    
        System.out.println("Citas agendadas:");
        List<Cita> citasTemp = new ArrayList<>();
        ColaDinamica<Cita> colaTemporal = new ColaDinamica<>();
    
        int index = 1;
        while (!colaCitas.estaVacia()) {
            try {
                Cita cita = colaCitas.eliminar(); 
                citasTemp.add(cita);              
                colaTemporal.agregar(cita);       
                System.out.println(index + ". " + cita);
                index++;
            } catch (Exception e) {
                System.out.println("Error al mostrar citas: " + e.getMessage());
            }
        }
    
        while (!colaTemporal.estaVacia()) {
            try {
                colaCitas.agregar(colaTemporal.eliminar());
            } catch (Exception e) {
                System.out.println("Error al restaurar citas: " + e.getMessage());
            }
        }
    
        System.out.print("Selecciona el número de la cita que deseas marcar como atendida: ");
        int seleccion = sc.nextInt();
        sc.nextLine();
    
        if (seleccion < 1 || seleccion > citasTemp.size()) {
            System.out.println("Selección inválida. Regresando al menú.");
        } else {
            Cita citaAtendida = citasTemp.remove(seleccion - 1);
            pilaCitas.push(citaAtendida);
            System.out.println("Cita marcada como atendida: " + citaAtendida);
    
            for (Cita cita : citasTemp) {
                colaCitas.agregar(cita);
            }
        }
    }

    private static void mostrarCitasAtendidas(PilaDinamica<Cita> pila) {
        if (pila.estaVacia()) {
            System.out.println("No hay citas atendidas.");
        } else {
            try {
                System.out.println("Citas atendidas:");
                while (!pila.estaVacia()) {
                    System.out.println(pila.pop());
                }
            } catch (Exception e) {
                System.out.println("Error al mostrar citas atendidas: " + e.getMessage());
            }
        }
    }

    private static void ordenarPacientesPorPrioridad() {
        ArrayList<Paciente> listaPacientes = arbolPacientes.obtenerDatos();

        ComparadorPorPrioridad comparador = new ComparadorPorPrioridad();
    
        QuicksortPacientes quicksort = new QuicksortPacientes(comparador);
        quicksort.quicksort(listaPacientes, 0, listaPacientes.size() - 1);
    
        System.out.println("Pacientes de mayor urgencia:");
        for (Paciente p : listaPacientes) {
            System.out.println(p);
        }
    }


}
