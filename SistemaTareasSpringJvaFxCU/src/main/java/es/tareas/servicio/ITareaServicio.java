package es.tareas.servicio;

import es.tareas.modelo.Tarea;

import java.util.List;

public interface ITareaServicio {

    public List<Tarea> listarTareas();

    public Tarea buscarTareaPorID(Integer idTarea);

    public  void guardarTarea(Tarea tarea);

    public void eliminarTarea(Tarea tarea);
}
