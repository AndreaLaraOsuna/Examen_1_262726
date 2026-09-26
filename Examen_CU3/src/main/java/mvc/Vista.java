/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package mvc;

import dominio.Alumno;
import dominio.Taller;
import dominio.Ticket;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ListSelectionModel;

/**
 *
 * @author Carmen Andrea Lara Osuna
 */
public class Vista implements IObservador {
 
    private Controlador controlador;
 
    private JFrame frame;
    private DefaultListModel<Taller> modeloListaUI;
    private JList<Taller> listaTalleresUI;
    private JTextArea areaDetalle;
    private JTextArea areaAlumnoOTicket;
    private JButton btnAccion;
    private boolean esperandoConfirmacion;
 
    public Vista() {
        this.esperandoConfirmacion = false;
        construirInterfaz();
    }
 
    public void setControlador(Controlador controlador) {
        this.controlador = controlador;
    }
 
    public void mostrar() {
        frame.setVisible(true);
    }
 
    /*
     * inicia el flujo inicial del caso de uso 
     */
    public void abrirPantalla() {
        controlador.iniciar();
    }
 
    private void construirInterfaz() {
        frame = new JFrame("Inscripcion a Taller de Semana ISW");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(820, 520);
        frame.setLayout(new BorderLayout(10, 10));
 
        modeloListaUI = new DefaultListModel<>();
        listaTalleresUI = new JList<>(modeloListaUI);
        listaTalleresUI.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        listaTalleresUI.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                onSeleccionTaller();
            }
        });
        JScrollPane scrollLista = new JScrollPane(listaTalleresUI);
        scrollLista.setPreferredSize(new Dimension(280, 400));
        scrollLista.setBorder(BorderFactory.createTitledBorder("Talleres"));
 
        areaDetalle = new JTextArea();
        areaDetalle.setEditable(false);
        areaDetalle.setLineWrap(true);
        areaDetalle.setWrapStyleWord(true);
        JScrollPane scrollDetalle = new JScrollPane(areaDetalle);
        scrollDetalle.setBorder(BorderFactory.createTitledBorder("Detalles del Taller"));
 
        areaAlumnoOTicket = new JTextArea();
        areaAlumnoOTicket.setEditable(false);
        areaAlumnoOTicket.setLineWrap(true);
        areaAlumnoOTicket.setWrapStyleWord(true);
        JScrollPane scrollAlumno = new JScrollPane(areaAlumnoOTicket);
        scrollAlumno.setBorder(BorderFactory.createTitledBorder("Datos del alumno / Ticket"));
 
        btnAccion = new JButton("Inscribirse");
        btnAccion.setEnabled(false);
        btnAccion.addActionListener(e -> onClicBotonPrincipal());
 
        JPanel panelBoton = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        panelBoton.add(btnAccion);
 
        JPanel panelDerecho = new JPanel(new GridLayout(2, 1, 10, 10));
        panelDerecho.add(scrollDetalle);
        panelDerecho.add(scrollAlumno);
 
        JPanel panelCentro = new JPanel(new BorderLayout(10, 10));
        panelCentro.add(panelDerecho, BorderLayout.CENTER);
        panelCentro.add(panelBoton, BorderLayout.SOUTH);
 
        frame.add(scrollLista, BorderLayout.WEST);
        frame.add(panelCentro, BorderLayout.CENTER);
    }
 
    // manejadores de eventos
 
    private void onSeleccionTaller() {
        Taller seleccionado = listaTalleresUI.getSelectedValue();
        if (seleccionado != null) {
            controlador.seleccionarTaller(seleccionado.getIdTaller());
        }
    }
 
    private void onClicBotonPrincipal() {
        if (!esperandoConfirmacion) {
            
            String texto = JOptionPane.showInputDialog(frame, "Ingrese su ID:");
            if (texto != null && !texto.trim().isEmpty()) {
                try {
                    int idAlumno = Integer.parseInt(texto.trim());
                    controlador.solicitarInscripcion(idAlumno);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame,
                            "El ID debe ser un numero.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
            // Si texto es null o el alumno dio Cancel no se llama al Controlador.
        } else {
            controlador.confirmarInscripcion();
        }
    }
 
    // Observador
 
    @Override
    public void actualizarListaTalleres(IModelo modelo) {
        modeloListaUI.clear();
        List<Taller> talleres = modelo.obtenerTalleres();
        for (Taller t : talleres) {
            modeloListaUI.addElement(t);
        }
    }
 
    @Override
    public void actualizarDetalleTaller(IModelo modelo) {
        Taller t = modelo.obtenerTallerSeleccionado();
        StringBuilder sb = new StringBuilder();
        if (t != null) {
            sb.append(t.getNombre()).append("\n");
            sb.append(t.getInstructor()).append("\n\n");
            sb.append(t.getFecha()).append("\n");
            sb.append(t.getHorario()).append("\n\n");
            sb.append("Cupo: ").append(t.getCupoDisponible());
        }
        areaDetalle.setText(sb.toString());
        areaAlumnoOTicket.setText("");
        esperandoConfirmacion = false;
        btnAccion.setText("Inscribirse");
        btnAccion.setEnabled(true);
    }
 
    @Override
    public void actualizarDatosAlumno(IModelo modelo) {
        Alumno a = modelo.obtenerAlumnoEncontrado();
        StringBuilder sb = new StringBuilder();
        sb.append("ID: ").append(a.getIdAlumno()).append("\n");
        sb.append("Nombre: ").append(a.getNombre()).append("\n");
        sb.append("Semestre: ").append(a.getSemestre()).append("\n");
        sb.append("Carrera: ").append(a.getCarrera());
        areaAlumnoOTicket.setText(sb.toString());
        esperandoConfirmacion = true;
        btnAccion.setText("Confirmar Inscripcion");
    }
 
    @Override
    public void actualizarTicket(IModelo modelo) {
        Ticket ticket = modelo.obtenerTicketGenerado();
        StringBuilder sb = new StringBuilder();
        sb.append("TICKET DE INSCRIPCION\n");
        sb.append("Folio: ").append(ticket.getFolio()).append("\n");
        sb.append("ID del alumno: ").append(ticket.getAlumno().getIdAlumno()).append("\n");
        sb.append("Nombre: ").append(ticket.getAlumno().getNombre()).append("\n");
        sb.append("Taller: ").append(ticket.getTaller().getNombre()).append("\n");
        sb.append("Instructor: ").append(ticket.getTaller().getInstructor()).append("\n");
        sb.append("Fecha: ").append(ticket.getTaller().getFecha()).append("\n");
        sb.append("Horario: ").append(ticket.getTaller().getHorario()).append("\n");
        sb.append("INSCRIPCION CONFIRMADA");
        areaAlumnoOTicket.setText(sb.toString());
        btnAccion.setEnabled(false);
        esperandoConfirmacion = false;
    }
 
    @Override
    public void actualizarError(IModelo modelo) {
        String mensaje = modelo.obtenerMensajeError();
        JOptionPane.showMessageDialog(frame, mensaje, "Aviso", JOptionPane.WARNING_MESSAGE);
        esperandoConfirmacion = false;
        btnAccion.setText("Inscribirse");
    }
}
 







