/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package mvc;

import dominio.Alumno;
import dominio.IDominio;
import dominio.Taller;
import dominio.Ticket;
import java.util.List;

/**
 *
 * @author Carmen Andrea Lara Osuna
 */
public class Modelo implements IModelo {
 
    private final IDominio dominio;
    private List<Taller> talleres;
    private Taller tallerSeleccionado;
    private Alumno alumnoEncontrado;
    private Ticket ticketGenerado;
    private String mensajeError;
    private IObservador observador;
 
    public Modelo(IDominio dominio) {
        this.dominio = dominio;
    }
 
    public void registrarObservador(IObservador observador) {
        this.observador = observador;
    }
 
    public void cargarTalleres() {
        talleres = dominio.obtenerTalleres();
        observador.actualizarListaTalleres(this);
    }
 
    public void seleccionarTaller(int idTaller) {
        tallerSeleccionado = dominio.obtenerTallerPorId(idTaller);
        observador.actualizarDetalleTaller(this);
    }
 
    public void solicitarInscripcion(int idAlumno) {
        alumnoEncontrado = dominio.buscarAlumnoPorId(idAlumno);
        if (alumnoEncontrado != null) {
            mensajeError = null;
            observador.actualizarDatosAlumno(this);
        } else {
            mensajeError = "No se encontro ningun alumno con el ID proporcionado.";
            observador.actualizarError(this);
        }
    }
 
    public void confirmarInscripcion() {
        ticketGenerado = dominio.confirmarInscripcion(
                tallerSeleccionado.getIdTaller(), alumnoEncontrado.getIdAlumno());
        if (ticketGenerado != null) {
            mensajeError = null;
            observador.actualizarTicket(this);
        } else {
            mensajeError = "No fue posible generar la inscripcion.";
            observador.actualizarError(this);
        }
    }
 
    @Override
    public List<Taller> obtenerTalleres() {
        return talleres;
    }
 
    @Override
    public Taller obtenerTallerSeleccionado() {
        return tallerSeleccionado;
    }
 
    @Override
    public Alumno obtenerAlumnoEncontrado() {
        return alumnoEncontrado;
    }
 
    @Override
    public Ticket obtenerTicketGenerado() {
        return ticketGenerado;
    }
 
    @Override
    public String obtenerMensajeError() {
        return mensajeError;
    }
}
 