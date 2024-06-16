package exa.arqweb.tp3.dto;

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

}
