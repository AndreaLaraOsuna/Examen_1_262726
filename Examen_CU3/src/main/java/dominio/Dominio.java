/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package dominio;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author Carmen Andrea Lara Osuna
 * 
 * Implementacion del IDominio.
 * Aqui vive toda la logica de negocio: filtrado de talleres disponibles,
 * busqueda de entidades y la operacion de confirmar inscripcion
 * tambien tiene listas precargadas con alumnos y talleres
 */
public class Dominio implements IDominio {
 
    private List<Taller> talleres;
    private List<Alumno> alumnos;
    private int contadorFolio;
 
    public Dominio() {
        talleres = new ArrayList<>();
        alumnos = new ArrayList<>();
        contadorFolio = 0;
        cargarDatosDePrueba();
    }
 
    private void cargarDatosDePrueba() {
        talleres.add(new Taller(1, "Inteligencia Artificial para Principiantes",
                "Ing. Ana Torres", LocalDate.of(2026, 9, 28), "10:00-12:00", 20));
        talleres.add(new Taller(2, "Git y GitHub desde Cero",
                "Ing. Miguel Lopez", LocalDate.of(2026, 9, 30), "13:00-15:00", 15));
        talleres.add(new Taller(3, "Desarrollo de Aplicaciones Moviles",
                "Ing. Laura Diaz", LocalDate.of(2026, 10, 1), "09:00-11:00", 18));
        talleres.add(new Taller(4, "Desarrollo Web Moderno",
                "Ing. Carlos Ruiz", LocalDate.of(2026, 10, 2), "11:00-13:00", 12));
        talleres.add(new Taller(5, "Ciberseguridad Basica",
                "Ing. Sofia Mendoza", LocalDate.of(2026, 10, 3), "15:00-17:00", 10));
        talleres.add(new Taller(6, "UI/UX",
                "Ing. Diego Fernandez", LocalDate.of(2026, 10, 5), "10:00-12:00", 14));
        talleres.add(new Taller(7, "Bases de Datos NoSQL",
                "Ing. Patricia Gomez", LocalDate.of(2026, 10, 6), "09:00-11:00", 16));
        talleres.add(new Taller(8, "Introduccion a la Nube (Cloud Computing)",
                "Ing. Roberto Sanchez", LocalDate.of(2026, 10, 7), "11:00-13:00", 20));
        talleres.add(new Taller(9, "Testing y Calidad de Software",
                "Ing. Fernanda Castro", LocalDate.of(2026, 10, 8), "13:00-15:00", 12));
        talleres.add(new Taller(10, "Metodologias Agiles con Scrum",
                "Ing. Ricardo Vega", LocalDate.of(2026, 10, 9), "15:00-17:00", 25));
 
        alumnos.add(new Alumno(256790, "Carlos Hernandez Ruiz", 5, "Ingenieria en Software"));
        alumnos.add(new Alumno(256791, "Maria Fernanda Lopez", 3, "Ingenieria en Software"));
        alumnos.add(new Alumno(256792, "Jose Luis Martinez", 7, "Ingenieria en Software"));
        alumnos.add(new Alumno(256793, "Ana Sofia Ramirez", 2, "Ingenieria en Software"));
        alumnos.add(new Alumno(256794, "Luis Fernando Torres", 6, "Ingenieria en Software"));
        alumnos.add(new Alumno(256795, "Daniela Guadalupe Perez", 4, "Ingenieria en Software"));
        alumnos.add(new Alumno(256796, "Miguel Angel Soto", 8, "Ingenieria en Software"));
        alumnos.add(new Alumno(256797, "Valeria Montserrat Diaz", 1, "Ingenieria en Software"));
        alumnos.add(new Alumno(256798, "Emiliano Garcia Rios", 5, "Ingenieria en Software"));
        alumnos.add(new Alumno(256799, "Ximena Castillo Ortiz", 3, "Ingenieria en Software"));
        alumnos.add(new Alumno(256800, "Diego Armando Flores", 9, "Ingenieria en Software"));
        alumnos.add(new Alumno(256801, "Regina Alejandra Nunez", 2, "Ingenieria en Software"));
        alumnos.add(new Alumno(256802, "Sebastian Morales Cruz", 6, "Ingenieria en Software"));
        alumnos.add(new Alumno(256803, "Fernanda Itzel Aguilar", 4, "Ingenieria en Software"));
        alumnos.add(new Alumno(256804, "Andres Ivan Reyes", 7, "Ingenieria en Software"));
        alumnos.add(new Alumno(256805, "Camila Sofia Mendez", 1, "Ingenieria en Software"));
        alumnos.add(new Alumno(256806, "Alejandro Vazquez Luna", 5, "Ingenieria en Software"));
        alumnos.add(new Alumno(256807, "Paulina Guadalupe Rios", 3, "Ingenieria en Software"));
        alumnos.add(new Alumno(256808, "Rodrigo Esteban Juarez", 8, "Ingenieria en Software"));
        alumnos.add(new Alumno(256809, "Melissa Carolina Ochoa", 2, "Ingenieria en Software"));
    }
 
    @Override
    public List<Taller> obtenerTalleres() {
        // Solo se muestran los talleres disponibles (con cupo), segun
        // el punto (a) de la descripcion del caso de uso.
        return talleres.stream()
                .filter(Taller::tieneCupoDisponible)
                .collect(Collectors.toList());
    }
 
    @Override
    public Taller obtenerTallerPorId(int idTaller) {
        for (Taller t : talleres) {
            if (t.getIdTaller() == idTaller) {
                return t;
            }
        }
        return null;
    }
 
    @Override
    public Alumno buscarAlumnoPorId(int idAlumno) {
        for (Alumno a : alumnos) {
            if (a.getIdAlumno() == idAlumno) {
                return a;
            }
        }
        return null;
    }
 
    @Override
    public Ticket confirmarInscripcion(int idTaller, int idAlumno) {
        Taller taller = obtenerTallerPorId(idTaller);
        Alumno alumno = buscarAlumnoPorId(idAlumno);
        if (taller == null || alumno == null) {
            return null;
        }
        taller.decrementarCupo();
        contadorFolio++;
        String folio = "TCK-" + String.format("%04d", contadorFolio);
        return new Ticket(folio, alumno, taller);
    }
}
 
