/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package dominio;

/**
 *
 * @author Carmen Andrea Lara Osuna
 */
public class Alumno {
 
    private int idAlumno;
    private String nombre;
    private int semestre;
    private String carrera;
 
    public Alumno(int idAlumno, String nombre, int semestre, String carrera) {
        this.idAlumno = idAlumno;
        this.nombre = nombre;
        this.semestre = semestre;
        this.carrera = carrera;
    }
 
    public int getIdAlumno() {
        return idAlumno;
    }
 
    public String getNombre() {
        return nombre;
    }
 
    public int getSemestre() {
        return semestre;
    }
 
    public String getCarrera() {
        return carrera;
    }
}
 