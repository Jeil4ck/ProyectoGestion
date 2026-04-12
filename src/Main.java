import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final List<Proyecto> proyectos = new ArrayList<>();
    private static Proyecto proyectoActual = null;


    public static void main(String[] args) {
        mostrarBienvenida();
        boolean salir = false;

        while (!salir) {
            mostrarEncabezado();
            mostrarMenu();
            int opcion = leerEntero("Selecciona una opción: ");

            switch (opcion) {
                case 1 -> crearProyecto();
                case 2 -> seleccionarProyecto();
                    case 3 -> agregarTarea();
                case 4 -> cambiarEstadoTarea();
                case 5 -> {
                    mostrarTareas();
                    presionarEnterParaContinuar();
                }
                case 6 -> eliminarTarea();
                case 7 -> {
                    listarProyectos();
                    presionarEnterParaContinuar();
                }
                case 0 -> salir = true;
                default -> System.out.println("Opción no válida. Intenta de nuevo.");
            }
        }

        System.out.println("¡Gracias por usar el Sistema de Gestión de Proyectos!");
    }
 

    private static void mostrarBienvenida() {
        System.out.println("============================================");
        System.out.println("  Sistema de Gestión de Proyectos de Software");
        System.out.println("============================================");
        System.out.println("Bienvenido. Crea proyectos, añade tareas, cambia estados y administra tus tareas.\n");
    }

      //codigo añadido para interfaz

    private static void mostrarEncabezado() {
    System.out.println("===============================================");
    System.out.println("   Sistema de Gestión de Proyectos - Mini Jira");
    System.out.println("===============================================");
    if (proyectoActual != null) {
        System.out.println("Proyecto activo: " + proyectoActual.getNombre());
        System.out.println("Tareas actuales: " + proyectoActual.obtenerCantidadTareas());
    } else {
        System.out.println("Proyecto activo: ninguno");
    }
    System.out.println("-----------------------------------------------");
}
    //final codigo



  private static void mostrarMenu() {
    System.out.println(" Menú principal");
    System.out.println(" 1. Crear proyecto");
    System.out.println(" 2. Seleccionar proyecto");
    System.out.println(" 3. Agregar tarea");
    System.out.println(" 4. Cambiar estado de tarea");
    System.out.println(" 5. Mostrar lista de tareas");
    System.out.println(" 6. Eliminar tarea");
    System.out.println(" 7. Listar proyectos");
    System.out.println(" 0. Salir");
    System.out.println("-----------------------------------------------");
}

    private static void crearProyecto() {
        System.out.print("Nombre del proyecto: ");
        String nombre = scanner.nextLine().trim();

        if (nombre.isEmpty()) {
            System.out.println("El nombre del proyecto no puede estar vacío.");
            return;
        }

        proyectos.add(new Proyecto(nombre));
        System.out.println("\n--- Proyecto '" + nombre + "' creado correctamente ---");
        System.out.println("-----------------------------------------------\n");
        presionarEnterParaContinuar();
    }

    private static void seleccionarProyecto() {
        if (proyectos.isEmpty()) {
            System.out.println("No hay proyectos disponibles. Crea uno primero.");
            return;
        }

        listarProyectos();
        int indice = leerEntero("Selecciona el número del proyecto: ") - 1;

        if (indice >= 0 && indice < proyectos.size()) {
            proyectoActual = proyectos.get(indice);
            System.out.println("\n--- Proyecto seleccionado: " + proyectoActual.getNombre() + " ---");
            System.out.println("-----------------------------------------------\n");
        } else {
            System.out.println("\n--- Selección incorrecta. Intenta otra vez. ---\n");
        }
        presionarEnterParaContinuar();
    }

    private static void agregarTarea() {
        if (!validarProyectoSeleccionado()) {
            return;
        }

        System.out.print("Título de la tarea: ");
        String titulo = scanner.nextLine().trim();
        System.out.print("Descripción de la tarea: ");
        String descripcion = scanner.nextLine().trim();

        if (titulo.isEmpty()) {
            System.out.println("El título de la tarea no puede estar vacío.");
            return;
        }

        proyectoActual.agregarTarea(titulo, descripcion);
        System.out.println("\n--- Tarea agregada al proyecto '" + proyectoActual.getNombre() + "' ---");
        System.out.println("-----------------------------------------------\n");
        presionarEnterParaContinuar();
    }

    private static void cambiarEstadoTarea() {
        if (!validarProyectoSeleccionado()) {
            return;
        }

        proyectoActual.mostrarTareas();
        if (proyectoActual.obtenerCantidadTareas() == 0) {
            return;
        }

        int id = leerEntero("Ingresa el ID de la tarea: ");
        Tarea tarea = proyectoActual.buscarTareaPorId(id);

        if (tarea == null) {
            System.out.println("\n--- No se encontró ninguna tarea con ese ID. ---\n");
            presionarEnterParaContinuar();
            return;
        }

        System.out.println("\nEstados disponibles:");
        for (Tarea.Estado estado : Tarea.Estado.values()) {
            System.out.println(estado.ordinal() + 1 + ". " + estado.getDescripcion());
        }

        int opcion = leerEntero("Selecciona el nuevo estado: ") - 1;
        if (opcion < 0 || opcion >= Tarea.Estado.values().length) {
            System.out.println("Estado inválido.");
            return;
        }

        tarea.setEstado(Tarea.Estado.values()[opcion]);
        System.out.println("\n--- Estado actualizado: " + tarea.getEstado().getDescripcion() + " ---");
        System.out.println("-----------------------------------------------\n");
        presionarEnterParaContinuar();
    }

    private static void mostrarTareas() {
        if (!validarProyectoSeleccionado()) {
            return;
        }

        System.out.println("\n----- Tareas del proyecto -----");
        proyectoActual.mostrarTareas();
        System.out.println("-----------------------------------------------\n");
    }

    private static void eliminarTarea() {
        if (!validarProyectoSeleccionado()) {
            return;
        }

        proyectoActual.mostrarTareas();
        if (proyectoActual.obtenerCantidadTareas() == 0) {
            return;
        }

        int id = leerEntero("Ingresa el ID de la tarea a eliminar: ");
        if (proyectoActual.eliminarTareaPorId(id)) {
            System.out.println("\n--- Tarea eliminada correctamente ---");
        } else {
            System.out.println("\n--- No se encontró ninguna tarea con ese ID. ---");
        }
        System.out.println("-----------------------------------------------\n");
        presionarEnterParaContinuar();
    }

    private static void listarProyectos() {
        if (proyectos.isEmpty()) {
            System.out.println("Aún no hay proyectos creados.");
            return;
        }

        System.out.println("\n----- Proyectos disponibles -----");
        for (int i = 0; i < proyectos.size(); i++) {
            Proyecto proyecto = proyectos.get(i);
            String seleccionado = proyecto.equals(proyectoActual) ? " [Seleccionado]" : "";
            System.out.println((i + 1) + ". " + proyecto.getNombre() + " - " + proyecto.obtenerCantidadTareas() + " tareas" + seleccionado);
        }
        System.out.println("-----------------------------------------------\n");
    }

    private static boolean validarProyectoSeleccionado() {
        if (proyectoActual == null) {
            System.out.println("Primero debes seleccionar un proyecto.");
            return false;
        }
        return true;
    }

    private static int leerEntero(String mensaje) {
        while (true) {
            System.out.print(mensaje);
            String linea = scanner.nextLine().trim();
            try {
                return Integer.parseInt(linea);
            } catch (NumberFormatException e) {
                System.out.println("Por favor ingresa un número válido.");
            }
        }
    }

    private static void presionarEnterParaContinuar() {
        System.out.print("Presiona Enter para continuar...");
        scanner.nextLine();
        System.out.println();
    }
}

