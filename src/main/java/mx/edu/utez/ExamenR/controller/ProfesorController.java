package mx.edu.utez.ExamenR.controller;

import jakarta.validation.Valid;
import mx.edu.utez.ExamenR.model.GradosAcademicos;
import mx.edu.utez.ExamenR.model.Profesor;
import mx.edu.utez.ExamenR.repositories.ProfesorRepository;
import mx.edu.utez.ExamenR.service.GradosAcademicosService;
import mx.edu.utez.ExamenR.service.GradosAcademicosServiceImpl;
import mx.edu.utez.ExamenR.service.ProfesorService;
import mx.edu.utez.ExamenR.service.ProfesorServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ProfesorController {
    @Autowired
    private ProfesorServiceImpl servicio;

    @Autowired
    private GradosAcademicosServiceImpl GAService;

    @GetMapping({ "/profesores", "/" })
    public String listarProfesor(Model modelo) {
        modelo.addAttribute("profesor", servicio.listarTodosLosProfesores());
        return "profesor"; // nos retorna al archivo estudiantes
    }

    @GetMapping("/profesores/mostrar/{id}")
    public String mostrarProfesor(@PathVariable long id, Model model){
        Profesor profesor = servicio.obtenerProfesorPorId(id);
        if (profesor != null){
            model.addAttribute("profesor", profesor);
            return "mostrar_profesor";
        }
        return "profesor";
    }


    @GetMapping("/profesores/nuevo")
    public String mostrarFormularioDeRegistrtarProfesor(Model modelo, Profesor profesor) {
        modelo.addAttribute("listarGrados", GAService.listarTodosLosGradosAcademicos());
        modelo.addAttribute("profesor", profesor);
        return "crear_profesor.html";
    }

    @PostMapping("/profesores")
    public String guardarProfesor(@Valid @ModelAttribute("profesor") Profesor profesor, BindingResult result, Model modelo) {
        modelo.addAttribute("listarGrados", GAService.listarTodosLosGradosAcademicos());
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
                                       Model modelo, BindingResult result) {

        Profesor profesorExistente = servicio.obtenerProfesorPorId(id);
        profesorExistente.setId(id);
        profesorExistente.setNombre(profesor.getNombre());
        profesorExistente.setPrimer_apellido(profesor.getPrimer_apellido());
        profesorExistente.setSegundo_apellido(profesor.getSegundo_apellido());
        profesorExistente.setTelefono(profesor.getTelefono());
        profesorExistente.setCorreo_electronico(profesor.getCorreo_electronico());
        profesorExistente.setSueldo(profesor.getSueldo());
        profesorExistente.setGradosAcademicos(profesor.getGradosAcademicos());
        profesorExistente.setFecha_registro(profesor.getFecha_registro());

        servicio.actualizarProfesor(profesorExistente);
        return "redirect:/profesores";
    }

    @GetMapping("/profesores/{id}")
    public String eliminarProfesor(@PathVariable Long id) {
        servicio.eliminarProfesor(id);
        return "redirect:/profesores";
    }
}
