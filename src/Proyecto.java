import java.util.ArrayList;
import java.util.List;

public class Proyecto {
    private final String nombre;
    private final List<Tarea> tareas;
    private int siguienteId;

    public Proyecto(String nombre) {
        this.nombre = nombre;
        this.tareas = new ArrayList<>();
        this.siguienteId = 1;
    }

    public String getNombre() {
        return nombre;
    }

    public void agregarTarea(String titulo, String descripcion) {
        tareas.add(new Tarea(siguienteId++, titulo, descripcion));
    }

    public boolean eliminarTareaPorId(int id) {
        return tareas.removeIf(tarea -> tarea.getId() == id);
    }

    @Override
    public String toString() {
        return nombre + " (" + obtenerCantidadTareas() + " tareas)";
    }

    public Tarea buscarTareaPorId(int id) {
        for (Tarea tarea : tareas) {
            if (tarea.getId() == id) {
                return tarea;
            }
        }
        return null;
    }

    public int obtenerCantidadTareas() {
        return tareas.size();
    }

    public List<Tarea> getTareas() {
        return new ArrayList<>(tareas);
    }

    public String obtenerTareasTexto() {
        if (tareas.isEmpty()) {
            return "No hay tareas en este proyecto.\n";
        }

        StringBuilder builder = new StringBuilder();
        for (Tarea tarea : tareas) {
            builder.append(tarea).append("\n");
        }
        return builder.toString();
    }

    public void mostrarTareas() {
        System.out.println("\nTareas del proyecto '" + nombre + "':");
        if (tareas.isEmpty()) {
            System.out.println("  No hay tareas en este proyecto.\n");
            return;
        }

        for (Tarea tarea : tareas) {
            System.out.println(tarea);
        }

        System.out.println();
    }
}

