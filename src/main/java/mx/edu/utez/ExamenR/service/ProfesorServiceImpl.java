package mx.edu.utez.ExamenR.service;

import mx.edu.utez.ExamenR.model.Profesor;
import mx.edu.utez.ExamenR.repositories.ProfesorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfesorServiceImpl implements ProfesorService{

    @Autowired
    private ProfesorRepository repository;

    @Override
    public List<Profesor> listarTodosLosProfesores() {
        return repository.findAll();
    }

    @Override
    public Profesor guardarProfesor(Profesor profesor) {
        return repository.save(profesor);
    }

    @Override
    public Profesor obtenerProfesorPorId(Long id) {
        return repository.findById(id).get();
    }

    @Override
    public Profesor actualizarProfesor(Profesor profesor) {
        return repository.save(profesor);
    }

    @Override
    public void eliminarProfesor(Long id) {
        repository.deleteById(id);

    }

}
