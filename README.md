# ProyectoGestion

Una aplicación de gestión de proyectos de software en Java, estilo mini Jira.

## Descripción

`ProyectoGestion` es un sistema básico para organizar proyectos y tareas desde la interfaz de usuario. Permite crear proyectos, añadir tareas, asignar estados y ver el avance de cada proyecto.

La versión actual incluye:

- Interfaz de consola clásica con menú interactivo
- Interfaz gráfica con Swing para una experiencia más moderna

## Funcionalidades

- Crear proyectos
- Seleccionar proyecto activo
- Agregar tareas a un proyecto
- Cambiar estado de tarea: `Pendiente`, `En progreso`, `Terminado`
- Ver lista de tareas por proyecto
- Eliminar tareas
- Navegar entre proyectos creados

## Estructura del proyecto

- `src/Main.java`: aplicación de consola
- `src/Interfaz.java`: interfaz gráfica Swing
- `src/Proyecto.java`: gestión de proyectos y tareas
- `src/Tarea.java`: modelo de tarea y estados

## Requisitos

- Java JDK 8 o superior instalado

## Ejecución

Desde la carpeta raíz del proyecto, abre una terminal y compila los archivos Java:

```bash
cd src
javac Main.java Proyecto.java Tarea.java Interfaz.java
```

### Ejecutar la versión de consola

```bash
java Main
```

### Ejecutar la interfaz gráfica

```bash
java Interfaz
```

## Uso

1. Crea un proyecto nuevo
2. Selecciona el proyecto que quieres gestionar
3. Agrega tareas y describe cada una
4. Cambia el estado de las tareas para hacer seguimiento
5. Elimina tareas cuando ya no sean necesarias

## Buenas prácticas

- No subas archivos compilados (`*.class`) al repositorio
- Usa `.gitignore` para mantener el repositorio limpio
- Mantén el código organizado en `src/`

## Repositorio

https://github.com/Jeil4ck/ProyectoGestion.git
