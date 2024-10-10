package es.estudiantes.servicio;

import es.estudiantes.modelo.Estudiante;

import java.util.List;

public interface IEstudianteServicio {

    public List<Estudiante> listarEstudiantes();
    public Estudiante buscarEstudiantePorId(Integer idEstudiante);
    public void guardaeEstudiante(Estudiante estudiante);
    public void eliminarEstudiante(Estudiante estudiante);
}
