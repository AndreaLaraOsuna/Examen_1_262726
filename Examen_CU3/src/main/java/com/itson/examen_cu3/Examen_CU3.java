/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.itson.examen_cu3;

import dominio.Dominio;
import dominio.IDominio;
import javax.swing.SwingUtilities;
import mvc.Controlador;
import mvc.Modelo;
import mvc.Vista;

/**
 *
 * @author calo2
 */
public class Examen_CU3 {

 
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            IDominio dominio = new Dominio();
            Modelo modelo = new Modelo(dominio);
            Vista vista = new Vista();
            Controlador controlador = new Controlador(modelo);
 
            vista.setControlador(controlador);
            modelo.registrarObservador(vista);
 
            vista.mostrar();
            vista.abrirPantalla();
        });
    }
}
 
