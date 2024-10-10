package es.estudiantes.servicio;

import es.estudiantes.modelo.Estudiante;
import es.estudiantes.repositorio.EstudianteRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EstudianteServicio  implements IEstudianteServicio{
    @Autowired //para poder inyectar las dependecnias de los objetos
    private EstudianteRepositorio estudianteRepositorio;
    @Override
    public List<Estudiante> listarEstudiantes() {
        List<Estudiante> estudiantes = estudianteRepositorio.findAll();
        return estudiantes;
    }

    @Override
    public Estudiante buscarEstudiantePorId(Integer idEstudiante) {
        Estudiante estudiante = estudianteRepositorio.findById(idEstudiante).orElse(null);
        return estudiante;
    }

    @Override
    public void guardaeEstudiante(Estudiante estudiante) {
        estudianteRepositorio.save(estudiante);

    }

    @Override
    public void eliminarEstudiante(Estudiante estudiante) {
        estudianteRepositorio.delete(estudiante);
    }
}
