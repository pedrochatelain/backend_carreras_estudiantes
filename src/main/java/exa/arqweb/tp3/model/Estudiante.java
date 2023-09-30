package exa.arqweb.tp3.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.ToString;

import java.util.List;
import java.util.LinkedList;

@Getter
@ToString
@Entity
public class Estudiante {

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

//    @OneToMany(mappedBy = "estudiante")
//    private List<Inscripcion> inscripciones;

    public Estudiante() {}

    public Estudiante(int libreta_universitaria, String nombre, String apellido, int edad, String genero, int dni, String ciudad_residencia) {
        this.libreta_universitaria = libreta_universitaria;
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
        this.genero = genero;
        this.dni = dni;
        this.ciudad_residencia = ciudad_residencia;
//        this.inscripciones = new LinkedList<Inscripcion>();
    }

}
