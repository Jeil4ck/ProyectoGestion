import javax.swing.*;
// Interfaz de usuario moderna para el gestor de proyectos
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class Interfaz {
    private final List<Proyecto> proyectos = new ArrayList<>();
    private Proyecto proyectoActual = null;

    private final JFrame frame = new JFrame("Gestor de Proyectos");
    private final DefaultListModel<Proyecto> modeloProyectos = new DefaultListModel<>();
    private final JList<Proyecto> listaProyectos = new JList<>(modeloProyectos);
    private final JTextArea areaTexto = new JTextArea();
    private final JLabel labelResumen = new JLabel();

    public Interfaz() {
        configurarVentana();
        frame.setVisible(true);
    }

    private void configurarVentana() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(980, 630);
        frame.setMinimumSize(new Dimension(900, 600));
        frame.setLocationRelativeTo(null);
        frame.setLayout(new BorderLayout(16, 16));

        Color fondo = new Color(22, 28, 48);
        Color panelOscuro = new Color(18, 23, 40);
        Color azul = new Color(50, 150, 255);
        Color texto = Color.WHITE;

        frame.getContentPane().setBackground(fondo);

        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(fondo);
        header.setBorder(new EmptyBorder(20, 24, 0, 24));

        JLabel titulo = new JLabel("Gestor Pro de Proyectos");
        titulo.setForeground(texto);
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 30));

        JLabel subtitulo = new JLabel("Visualiza todos tus proyectos y gestiona tus  tareas.");
        subtitulo.setForeground(new Color(200, 210, 255));
        subtitulo.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        header.add(titulo, BorderLayout.NORTH);
        header.add(subtitulo, BorderLayout.SOUTH);

        JPanel panelCentral = new JPanel(new BorderLayout(16, 16));
        panelCentral.setBackground(fondo);
        panelCentral.setBorder(new EmptyBorder(0, 24, 0, 24));

        JPanel panelIzquierdo = new JPanel(new BorderLayout(12, 12));
        panelIzquierdo.setBackground(panelOscuro);
        panelIzquierdo.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(70, 90, 160), 2),
                new EmptyBorder(16, 16, 16, 16)
        ));

        JLabel labelProyectos = new JLabel("Proyectos creados");
        labelProyectos.setForeground(texto);
        labelProyectos.setFont(new Font("Segoe UI", Font.BOLD, 16));

        listaProyectos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        listaProyectos.setBackground(new Color(12, 16, 32));
        listaProyectos.setForeground(texto);
        listaProyectos.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        listaProyectos.setSelectionBackground(azul);
        listaProyectos.setFixedCellHeight(40);
        listaProyectos.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                proyectoActual = listaProyectos.getSelectedValue();
                actualizarDetalle();
            }
        });

        JScrollPane scrollProyectos = new JScrollPane(listaProyectos);
        scrollProyectos.setBorder(BorderFactory.createLineBorder(new Color(70, 90, 160), 1));

        panelIzquierdo.add(labelProyectos, BorderLayout.NORTH);
        panelIzquierdo.add(scrollProyectos, BorderLayout.CENTER);

        JPanel panelDerecho = new JPanel(new BorderLayout(12, 12));
        panelDerecho.setBackground(panelOscuro);
        panelDerecho.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(70, 90, 160), 2),
                new EmptyBorder(16, 16, 16, 16)
        ));

        labelResumen.setForeground(texto);
        labelResumen.setFont(new Font("Segoe UI", Font.BOLD, 18));
        labelResumen.setText("Selecciona un proyecto para ver detalles.");

        areaTexto.setEditable(false);
        areaTexto.setBackground(new Color(12, 16, 32));
        areaTexto.setForeground(texto);
        areaTexto.setFont(new Font("Consolas", Font.PLAIN, 14));
        areaTexto.setBorder(BorderFactory.createEmptyBorder(12, 12, 12, 12));
        areaTexto.setText("Aquí verás las tareas del proyecto seleccionado.");

        JScrollPane scrollDetalle = new JScrollPane(areaTexto);
        scrollDetalle.setBorder(BorderFactory.createLineBorder(new Color(70, 90, 160), 1));

        panelDerecho.add(labelResumen, BorderLayout.NORTH);
        panelDerecho.add(scrollDetalle, BorderLayout.CENTER);

        panelCentral.add(panelIzquierdo, BorderLayout.WEST);
        panelCentral.add(panelDerecho, BorderLayout.CENTER);

        JPanel panelBotones = new JPanel(new GridLayout(1, 6, 12, 0));
        panelBotones.setBackground(fondo);
        panelBotones.setBorder(new EmptyBorder(0, 24, 24, 24));

        panelBotones.add(crearBoton("Nuevo proyecto", azul, e -> crearProyecto()));
        panelBotones.add(crearBoton("Seleccionar", azul, e -> seleccionarProyecto()));
        panelBotones.add(crearBoton("Agregar tarea", azul, e -> agregarTarea()));
        panelBotones.add(crearBoton("Cambiar estado", azul, e -> cambiarEstadoTarea()));
        panelBotones.add(crearBoton("Eliminar tarea", azul, e -> eliminarTarea()));
        panelBotones.add(crearBoton("Actualizar lista", azul, e -> actualizarListaProyectos()));

        frame.add(header, BorderLayout.NORTH);
        frame.add(panelCentral, BorderLayout.CENTER);
        frame.add(panelBotones, BorderLayout.SOUTH);

        actualizarListaProyectos();
        actualizarDetalle();
    }

    private JButton crearBoton(String texto, Color color, ActionListener accion) {
        JButton boton = new JButton(texto);
        boton.setFocusPainted(false);
        boton.setBackground(color);
        boton.setForeground(Color.WHITE);
        boton.setFont(new Font("Segoe UI", Font.BOLD, 13));
        boton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        boton.setBorder(BorderFactory.createEmptyBorder(10, 14, 10, 14));
        boton.addActionListener(accion);
        return boton;
    }

    private void crearProyecto() {
        String nombre = JOptionPane.showInputDialog(frame, "Nombre del proyecto:", "Nuevo proyecto", JOptionPane.PLAIN_MESSAGE);
        if (nombre == null || nombre.trim().isEmpty()) return;

        Proyecto proyecto = new Proyecto(nombre.trim());
        proyectos.add(proyecto);
        actualizarListaProyectos();
        listaProyectos.setSelectedValue(proyecto, true);
        mostrarMensaje("Proyecto creado correctamente.");
    }

    private void seleccionarProyecto() {
        Proyecto seleccionado = listaProyectos.getSelectedValue();
        if (seleccionado == null) {
            mostrarMensaje("Selecciona un proyecto de la lista.");
            return;
        }
        proyectoActual = seleccionado;
        actualizarDetalle();
        mostrarMensaje("Proyecto seleccionado: " + proyectoActual.getNombre());
    }

    private void agregarTarea() {
        if (!validarProyecto()) return;

        String titulo = JOptionPane.showInputDialog(frame, "Título de la tarea:", "Nueva tarea", JOptionPane.PLAIN_MESSAGE);
        if (titulo == null || titulo.trim().isEmpty()) return;

        String descripcion = JOptionPane.showInputDialog(frame, "Descripción de la tarea:", "Nueva tarea", JOptionPane.PLAIN_MESSAGE);
        if (descripcion == null) descripcion = "";

        proyectoActual.agregarTarea(titulo.trim(), descripcion.trim());
        actualizarDetalle();
        actualizarListaProyectos();
        mostrarMensaje("Tarea agregada a " + proyectoActual.getNombre() + ".");
    }

    private void cambiarEstadoTarea() {
        if (!validarProyecto()) return;

        if (proyectoActual.obtenerCantidadTareas() == 0) {
            mostrarMensaje("No hay tareas en este proyecto.");
            return;
        }

        String idStr = JOptionPane.showInputDialog(frame, "ID de la tarea:", "Cambiar estado", JOptionPane.PLAIN_MESSAGE);
        if (idStr == null || idStr.trim().isEmpty()) return;

        try {
            int id = Integer.parseInt(idStr.trim());
            Tarea tarea = proyectoActual.buscarTareaPorId(id);
            if (tarea == null) {
                mostrarMensaje("No se encontró tarea con ese ID.");
                return;
            }

            String[] opciones = {"Pendiente", "En progreso", "Terminado"};
            int op = JOptionPane.showOptionDialog(
                    frame,
                    "Selecciona el nuevo estado:",
                    "Estado de tarea",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.PLAIN_MESSAGE,
                    null,
                    opciones,
                    opciones[tarea.getEstado().ordinal()]
            );

            if (op >= 0) {
                tarea.setEstado(Tarea.Estado.values()[op]);
                actualizarDetalle();
                mostrarMensaje("Estado actualizado a " + tarea.getEstado().getDescripcion() + ".");
            }
        } catch (NumberFormatException ex) {
            mostrarMensaje("Ingresa un ID válido.");
        }
    }

    private void mostrarTareas() {
        if (!validarProyecto()) return;
        actualizarDetalle();
    }

    private void eliminarTarea() {
        if (!validarProyecto()) return;

        if (proyectoActual.obtenerCantidadTareas() == 0) {
            mostrarMensaje("No hay tareas para eliminar.");
            return;
        }

        String idStr = JOptionPane.showInputDialog(frame, "ID de la tarea a eliminar:", "Eliminar tarea", JOptionPane.PLAIN_MESSAGE);
        if (idStr == null || idStr.trim().isEmpty()) return;

        try {
            int id = Integer.parseInt(idStr.trim());
            if (proyectoActual.eliminarTareaPorId(id)) {
                actualizarDetalle();
                actualizarListaProyectos();
                mostrarMensaje("Tarea eliminada.");
            } else {
                mostrarMensaje("No se encontró tarea con ese ID.");
            }
        } catch (NumberFormatException ex) {
            mostrarMensaje("Ingresa un ID válido.");
        }
    }

    private boolean validarProyecto() {
        if (proyectoActual == null) {
            mostrarMensaje("Selecciona un proyecto primero.");
            return false;
        }
        return true;
    }

    private void actualizarListaProyectos() {
        modeloProyectos.clear();
        for (Proyecto proyecto : proyectos) {
            modeloProyectos.addElement(proyecto);
        }
    }

    private void actualizarDetalle() {
        if (proyectoActual == null) {
            labelResumen.setText("Selecciona un proyecto para ver detalles.");
            areaTexto.setText("Aquí verás las tareas del proyecto seleccionado.");
            return;
        }

        labelResumen.setText("Proyecto: " + proyectoActual.getNombre() + " — " + proyectoActual.obtenerCantidadTareas() + " tareas");
        String texto = proyectoActual.obtenerTareasTexto();
        if (texto.isEmpty()) {
            texto = "No hay tareas. Agrega una tarea para comenzar.";
        }
        areaTexto.setText(texto);
    }

    private void mostrarMensaje(String msg) {
        JOptionPane.showMessageDialog(frame, msg, "Gestión de Proyectos", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Interfaz::new);
    }
}
