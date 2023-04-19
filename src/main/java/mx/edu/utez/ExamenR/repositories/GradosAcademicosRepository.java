package mx.edu.utez.ExamenR.repositories;

import mx.edu.utez.ExamenR.model.GradosAcademicos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GradosAcademicosRepository extends JpaRepository<GradosAcademicos, Long> {
}
