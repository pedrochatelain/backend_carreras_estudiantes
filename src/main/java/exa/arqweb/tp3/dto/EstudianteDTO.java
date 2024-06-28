package exa.arqweb.tp3.dto;

import exa.arqweb.tp3.model.Estudiante;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;

@Getter
@Setter
public class EstudianteDTO {

    private Integer libreta_universitaria;
    private String nombre;
    private String apellido;
    private int edad;
    private int dni;
    private String ciudad_residencia;
    private String genero;
    private LocalDate fecha_nacimiento;

    public EstudianteDTO() {}

    public EstudianteDTO(Integer libreta_universitaria, String nombre, String apellido, int edad, int dni, String ciudad_residencia, String genero) {
        this.libreta_universitaria = libreta_universitaria;
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
        this.dni = dni;
        this.ciudad_residencia = ciudad_residencia;
        this.genero = genero;
    }

    public EstudianteDTO(String nombre, String apellido, int edad, int dni, String ciudad_residencia, String genero) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
        this.dni = dni;
        this.ciudad_residencia = ciudad_residencia;
        this.genero = genero;
    }

    public EstudianteDTO(String nombre, int edad) {
        this.nombre = nombre;
        this.edad = edad;
    }

    public EstudianteDTO(String nombre, String apellido, LocalDate fecha_nacimiento) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.fecha_nacimiento = fecha_nacimiento;
    }

    private void lowercaseStringFields() {
        if (nombre != null)
            nombre = nombre.toLowerCase();
        if (apellido != null)
            apellido = apellido.toLowerCase();
        if (ciudad_residencia != null)
            ciudad_residencia = ciudad_residencia.toLowerCase();
        if (genero != null)
            genero = genero.toLowerCase();
    }

    public Estudiante toEstudiante() {
        this.lowercaseStringFields();
        Estudiante est = new Estudiante();
        est.setNombre(nombre);
        est.setApellido(apellido);
        est.setEdad(edad);
        est.setGenero(genero);
        est.setDni(dni);
        est.setCiudad_residencia(ciudad_residencia);
        est.setFecha_nacimiento(fecha_nacimiento);
        return est;
    }
}
