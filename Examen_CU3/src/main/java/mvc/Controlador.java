/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package mvc;

/**
 *
 * @author Carmen Andrea Lara Osuna
 */
public class Controlador {
 
    private final Modelo modelo;
 
    public Controlador(Modelo modelo) {
        this.modelo = modelo;
    }
 
    public void iniciar() {
        modelo.cargarTalleres();
    }
 
    public void seleccionarTaller(int idTaller) {
        modelo.seleccionarTaller(idTaller);
    }
 
    public void solicitarInscripcion(int idAlumno) {
        modelo.solicitarInscripcion(idAlumno);
    }
 
    public void confirmarInscripcion() {
        modelo.confirmarInscripcion();
    }
}
 