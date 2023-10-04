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

//    @OneToMany(mappedBy = "carrera")
//    private List<Inscripcion> inscripciones;

    public Carrera() {
//        this.inscripciones = new LinkedList<>();
    }

    public Carrera(String nombre) {
        this.nombre = nombre;
//        this.inscripciones = new LinkedList<>();
    }

//    public void addInscripcion(Inscripcion i) {
//        this.inscripciones.add(i);
//    }

    @Override
    public String toString() {
        return "\nID: " + id +
                "\nNombre: " + nombre;
    }

}
