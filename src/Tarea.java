public class Tarea {
    public enum Estado {
        PENDIENTE("Pendiente"),
        EN_PROGRESO("En progreso"),
        TERMINADO("Terminado");

        private final String descripcion;

        Estado(String descripcion) {
            this.descripcion = descripcion;
        }

        public String getDescripcion() {
            return descripcion;
        }
    }

    private final int id;
    private final String titulo;
    private final String descripcion;
    private Estado estado;

    public Tarea(int id, String titulo, String descripcion) {
        this.id = id;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.estado = Estado.PENDIENTE;
    }

    public int getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "[" + id + "] " + titulo + " - " + descripcion + " (" + estado.getDescripcion() + ")";
    }
}

