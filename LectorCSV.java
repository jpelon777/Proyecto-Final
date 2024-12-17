            
/**
 * Write a description of class lector_csv here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.io.*;
import java.util.*;

public class LectorCSV {
    public static void leerPacientes(String rutaArchivo, ArbolAVL<Paciente> arbolPacientes) {
        try (BufferedReader br = new BufferedReader(new FileReader(rutaArchivo))) {
            String linea;
            br.readLine(); 
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(",");
                int id = Integer.parseInt(datos[0].trim());
                String nombre = datos[1].trim();
                int edad = Integer.parseInt(datos[2].trim());
                String diagnostico = datos[3].trim();
                int prioridad = Integer.parseInt(datos[4].trim()); 

                Paciente paciente = new Paciente(id, nombre, edad, diagnostico, prioridad);
                arbolPacientes.insertar(paciente);
            }
        } catch (IOException e) {
            System.out.println("Error al leer pacientes: " + e.getMessage());
        }
    }

    public static void leerDoctores(String rutaArchivo, ArbolAVL<Doctor> arbolDoctores) {
        try (BufferedReader br = new BufferedReader(new FileReader(rutaArchivo))) {
            String linea;
            br.readLine(); 
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(",");
                int id = Integer.parseInt(datos[0].trim());
                String nombre = datos[1].trim();
                String especialidad = datos[2].trim();

                Doctor doctor = new Doctor(id, nombre, especialidad);
                arbolDoctores.insertar(doctor);
            }
        } catch (IOException e) {
            System.out.println("Error al leer doctores: " + e.getMessage());
        }
    }

    public static void leerAreasConConexiones(String rutaArchivo, Grafo grafoAreas) {
        try (BufferedReader br = new BufferedReader(new FileReader(rutaArchivo))) {
            String linea;
            br.readLine(); 
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(",");
                String nombre = datos[0].trim();
                String conexiones = datos[1].trim();

                grafoAreas.addVertice(nombre);

                if (!conexiones.isEmpty()) {
                    String[] areasConectadas = conexiones.split(";");
                    for (String areaConectada : areasConectadas) {
                        grafoAreas.agregarArista(nombre, areaConectada.trim());
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Error al leer áreas: " + e.getMessage());
        }
    }
}
