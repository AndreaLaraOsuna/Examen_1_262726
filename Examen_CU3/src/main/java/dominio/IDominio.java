/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */

package dominio;

import java.util.List;

/**
 *
 * @author Carmen Andrea Lara
 * Interfaz que expone dominio hacia el MVC
 */
public interface IDominio {
   List<Taller> obtenerTalleres();
 
    Taller obtenerTallerPorId(int idTaller);
 
    Alumno buscarAlumnoPorId(int idAlumno);
 
    Ticket confirmarInscripcion(int idTaller, int idAlumno);
}
 