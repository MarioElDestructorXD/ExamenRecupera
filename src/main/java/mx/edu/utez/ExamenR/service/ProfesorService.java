package mx.edu.utez.ExamenR.service;

import mx.edu.utez.ExamenR.model.Profesor;

import java.util.List;

public interface ProfesorService {
    public List<Profesor> listarTodosLosProfesores();

    public Profesor guardarProfesor(Profesor profesor);

    public Profesor obtenerProfesorPorId(Long id);

    public Profesor actualizarProfesor(Profesor profesor);

    public void eliminarProfesor(Long id);
}
