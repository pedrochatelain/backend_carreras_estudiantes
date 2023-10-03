package exa.arqweb.tp3.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@NamedNativeQuery(
        name = "get_reporte_carreras",
        query = """
        SELECT nombre AS carrera, anio_graduacion AS anio, MAX(cant_inscriptos) AS cant_inscriptos, MAX(cant_graduados) AS cant_egresados
        FROM (
             SELECT c.nombre, anio_graduacion, COUNT(anio_graduacion) AS cant_graduados, 0 AS cant_inscriptos
             FROM carrera c LEFT JOIN inscripcion i ON i.id_carrera = c.id
             WHERE anio_graduacion IS NOT NULL
             GROUP BY c.id, anio_graduacion
             UNION (
                 SELECT c.nombre, anio_inscripcion, 0, COUNT(anio_inscripcion) AS cant_inscriptos
                 FROM carrera c LEFT JOIN inscripcion i ON i.id_carrera = c.id
                 GROUP BY c.id, anio_inscripcion
             )
        ) graduados_inscriptos
        GROUP BY nombre, anio
        ORDER BY nombre, anio
        """,
        resultSetMapping = "reporte_carrera_mapping" // se encuentra en resources/META-INF/mappings.xml
)
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
