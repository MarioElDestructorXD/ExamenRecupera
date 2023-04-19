package mx.edu.utez.ExamenR.controller;

import jakarta.validation.Valid;
import mx.edu.utez.ExamenR.model.Profesor;
import mx.edu.utez.ExamenR.repositories.ProfesorRepository;
import mx.edu.utez.ExamenR.service.GradosAcademicosService;
import mx.edu.utez.ExamenR.service.ProfesorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ProfesorController {
    @Autowired
    private ProfesorService servicio;

    @Autowired
    private GradosAcademicosService GAService;

    @GetMapping({ "/profesores", "/" })
    public String listarProfesor(Model modelo) {
        modelo.addAttribute("profesor", servicio.listarTodosLosProfesores());
        return "profesor"; // nos retorna al archivo estudiantes
    }

    @GetMapping("/profesores/nuevo")
    public String mostrarFormularioDeRegistrtarProfesor(Model modelo) {
        Profesor profesor = new Profesor();
        modelo.addAttribute("listarGrados", GAService.listarTodosLosGradosAcademicos());
        modelo.addAttribute("profesor", profesor);
        return "crear_profesor.html";
    }

    @PostMapping("/profesores")
    public String guardarProfesor(@Valid @ModelAttribute("profesor") Profesor profesor, BindingResult result) {
        if (result.hasErrors()){
            for (ObjectError error: result.getAllErrors()){
                System.out.println("Error: " + error.getDefaultMessage());
            }
            return "crear_profesor";
        }
        servicio.guardarProfesor(profesor);
        return "redirect:/profesores";
    }

    @GetMapping("/profesores/editar/{id}")
    public String mostrarFormularioDeEditar(@PathVariable Long id, Model modelo) {
        modelo.addAttribute("listarGrados", GAService.listarTodosLosGradosAcademicos());
        modelo.addAttribute("profesor", servicio.obtenerProfesorPorId(id));
        return "editar_profesor";
    }

    @PostMapping("/profesores/{id}")
    public String actualizarProfesor(@PathVariable Long id, @ModelAttribute("profesor") Profesor profesor,
                                       Model modelo) {
        Profesor profesorExistente = servicio.obtenerProfesorPorId(id);
        profesorExistente.setId(id);
        profesorExistente.setNombre(profesor.getNombre());
        profesorExistente.setPrimer_apellido(profesor.getPrimer_apellido());
        profesorExistente.setSegundo_apellido(profesor.getSegundo_apellido());
        profesorExistente.setTelefono(profesor.getTelefono());
        profesorExistente.setCorreo_electronico(profesor.getCorreo_electronico());
        profesorExistente.setSueldo(profesor.getSueldo());
        profesorExistente.setGradosAcademicos(profesor.getGradosAcademicos());

        servicio.actualizarProfesor(profesorExistente);
        return "redirect:/profesores";
    }

    @GetMapping("/profesores/{id}")
    public String eliminarProfesor(@PathVariable Long id) {
        servicio.eliminarProfesor(id);
        return "redirect:/profesores";
    }
}
