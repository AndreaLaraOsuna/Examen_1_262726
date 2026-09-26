/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package dominio;

/**
 *
 * @author Carmen Andrea Lara Osuna
 */
public class Ticket {
 
    private String folio;
    private Alumno alumno;
    private Taller taller;
    private boolean confirmado;
 
    public Ticket(String folio, Alumno alumno, Taller taller) {
        this.folio = folio;
        this.alumno = alumno;
        this.taller = taller;
        this.confirmado = true;
    }
 
    public String getFolio() {
        return folio;
    }
 
    public Alumno getAlumno() {
        return alumno;
    }
 
    public Taller getTaller() {
        return taller;
    }
 
    public boolean isConfirmado() {
        return confirmado;
    }
}