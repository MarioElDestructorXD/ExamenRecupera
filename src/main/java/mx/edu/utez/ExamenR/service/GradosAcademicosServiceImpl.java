package mx.edu.utez.ExamenR.service;

import mx.edu.utez.ExamenR.model.GradosAcademicos;
import mx.edu.utez.ExamenR.repositories.GradosAcademicosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GradosAcademicosServiceImpl implements GradosAcademicosService{

    @Autowired
    private GradosAcademicosRepository gradosAcademicosRepository;
    @Override
    public List<GradosAcademicos> listarTodosLosGradosAcademicos() {
        return gradosAcademicosRepository.findAll(Sort.by("nombre").ascending());
    }
}
