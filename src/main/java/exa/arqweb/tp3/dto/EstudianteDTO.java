package exa.arqweb.tp3.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EstudianteDTO {

    @NotNull
    private Integer libreta_universitaria;

    private String nombre;
    private String apellido;
    private int edad;
    private int dni;
    private String ciudad_residencia;
    private String genero;

    public EstudianteDTO(Integer libreta_universitaria, String nombre, String apellido, int edad, int dni, String ciudad_residencia, String genero) {
        this.libreta_universitaria = libreta_universitaria;
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
        this.dni = dni;
        this.ciudad_residencia = ciudad_residencia;
        this.genero = genero;
    }
}
