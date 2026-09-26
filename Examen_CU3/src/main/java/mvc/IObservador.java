/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */

package mvc;

/**
 *
 * @author Carmen Andrea Lara
 */
public interface IObservador {
 
    void actualizarListaTalleres(IModelo modelo);
 
    void actualizarDetalleTaller(IModelo modelo);
 
    void actualizarDatosAlumno(IModelo modelo);
 
    void actualizarTicket(IModelo modelo);
 
    void actualizarError(IModelo modelo);
}
 