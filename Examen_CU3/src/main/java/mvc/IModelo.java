/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */

package mvc;

import dominio.Alumno;
import dominio.Taller;
import dominio.Ticket;
import java.util.List;

/**
 *
 * @author Carmen Andrea Lara
 */
public interface IModelo {

    List<Taller> obtenerTalleres();
 
    Taller obtenerTallerSeleccionado();
 
    Alumno obtenerAlumnoEncontrado();
 
    Ticket obtenerTicketGenerado();
 
    String obtenerMensajeError();
}
 