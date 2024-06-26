package exa.arqweb.tp3.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@ToString
public class Estudiante {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private int libreta_universitaria;

    @Column
    private String nombre;

    @Column
    private String apellido;

    @Column
    private int edad;

    @Column
    private String genero;

    @Column
    private int dni;

    @Column
    private String ciudad_residencia;

    @Column
    private LocalDate fecha_nacimiento;

    public Estudiante() {}

    public Estudiante(int libreta_universitaria, String nombre, String apellido, int edad, String genero, int dni, String ciudad_residencia, LocalDate fecha_nacimiento) {
        this.libreta_universitaria = libreta_universitaria;
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
        this.genero = genero;
        this.dni = dni;
        this.ciudad_residencia = ciudad_residencia;
        this.fecha_nacimiento = fecha_nacimiento;
    }

}