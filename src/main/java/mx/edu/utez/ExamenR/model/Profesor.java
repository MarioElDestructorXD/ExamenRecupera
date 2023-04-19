package mx.edu.utez.ExamenR.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;
@Entity
@Table(name = "profesores")
public class Profesor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    @Column(nullable = false, length = 45)
    private String nombre;
    @NotBlank
    @Column(nullable = false, length = 45)
    private String primer_apellido;
    @Column(length = 45)
    private String segundo_apellido;
    @NotNull
    @Column(nullable = false, length = 10)
    private String telefono;
    @Email
    @Column(nullable = false, length = 60)
    private String correo_electronico;
    @NotNull
    @Min(0)
    @Column(nullable = false)
    private float sueldo;
    @Column(name = "fecha_registro", nullable = false)
    @CreationTimestamp
    private Date fecha_registro;
    @NotNull
    @ManyToOne
    @JoinColumn(name = "grado_academico_id", nullable = false)
    private GradosAcademicos gradosAcademicos;

    public Profesor() {
    }

    public Profesor(Long id, String nombre, String primer_apellido, String segundo_apellido, String telefono, String correo_electronico, float sueldo, Date fecha_registro, GradosAcademicos gradosAcademicos) {
        this.id = id;
        this.nombre = nombre;
        this.primer_apellido = primer_apellido;
        this.segundo_apellido = segundo_apellido;
        this.telefono = telefono;
        this.correo_electronico = correo_electronico;
        this.sueldo = sueldo;
        this.fecha_registro = fecha_registro;
        this.gradosAcademicos = gradosAcademicos;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPrimer_apellido() {
        return primer_apellido;
    }

    public void setPrimer_apellido(String primer_apellido) {
        this.primer_apellido = primer_apellido;
    }

    public String getSegundo_apellido() {
        return segundo_apellido;
    }

    public void setSegundo_apellido(String segundo_apellido) {
        this.segundo_apellido = segundo_apellido;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreo_electronico() {
        return correo_electronico;
    }

    public void setCorreo_electronico(String correo_electronico) {
        this.correo_electronico = correo_electronico;
    }

    public float getSueldo() {
        return sueldo;
    }

    public void setSueldo(float sueldo) {
        this.sueldo = sueldo;
    }

    public Date getFecha_registro() {
        return fecha_registro;
    }

    public void setFecha_registro(Date fecha_registro) {
        this.fecha_registro = fecha_registro;
    }

    public GradosAcademicos getGradosAcademicos() {
        return gradosAcademicos;
    }

    public void setGradosAcademicos(GradosAcademicos gradosAcademicos) {
        this.gradosAcademicos = gradosAcademicos;
    }
}

