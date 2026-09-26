/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package dominio;

import java.time.LocalDate;

/**
 *
 * @author Carmen Andrea Lara Osuna
 */
public class Taller {
 
    private int idTaller;
    private String nombre;
    private String instructor;
    private LocalDate fecha;
    private String horario;
    private int cupoMaximo;
    private int cupoDisponible;
 
    public Taller(int idTaller, String nombre, String instructor, LocalDate fecha,
                  String horario, int cupoMaximo) {
        this.idTaller = idTaller;
        this.nombre = nombre;
        this.instructor = instructor;
        this.fecha = fecha;
        this.horario = horario;
        this.cupoMaximo = cupoMaximo;
        this.cupoDisponible = cupoMaximo;
    }
 
    public int getIdTaller() {
        return idTaller;
    }
 
    public String getNombre() {
        return nombre;
    }
 
    public String getInstructor() {
        return instructor;
    }
 
    public LocalDate getFecha() {
        return fecha;
    }
 
    public String getHorario() {
        return horario;
    }
 
    public int getCupoMaximo() {
        return cupoMaximo;
    }
 
    public int getCupoDisponible() {
        return cupoDisponible;
    }
 
    public boolean tieneCupoDisponible() {
        return cupoDisponible > 0;
    }
 
    public void decrementarCupo() {
        if (cupoDisponible > 0) {
            cupoDisponible--;
        }
    }
 
    @Override
    public String toString() {
        return nombre;
    }
}
