package exa.arqweb.tp3.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Carrera {

    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Id
    private int id;

    @Column
    private String nombre;

    public Carrera() {}

    public Carrera(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "\nID: " + id +
                "\nNombre: " + nombre;
    }

}