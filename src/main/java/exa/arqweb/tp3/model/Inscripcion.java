package exa.arqweb.tp3.model;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Entity
@Table(name = "inscripcion")
@Getter
@ToString
public class Inscripcion {

    @EmbeddedId
    private InscripcionKey id;

    @ManyToOne
    @MapsId("libreta_universitaria")
    @JoinColumn(name = "id_estudiante")
    private Estudiante estudiante;

    @ManyToOne
    @MapsId("id")
    @JoinColumn(name = "id_carrera")
    private Carrera carrera;

    @Column
    private Integer anio_inscripcion;

    @Column
    private Integer anio_graduacion;

    public Inscripcion() {}

    public Inscripcion(Estudiante e, Carrera c, int anio_inscripcion) {
        this.id = new InscripcionKey(e.getLibreta_universitaria(), c.getId());
        this.estudiante = e;
        this.carrera = c;
        this.anio_inscripcion = anio_inscripcion;
    }

    public Integer getAnioInscripcion() {
        return anio_inscripcion;
    }

    public void setAnioGraduacion(Integer anio_graduacion) {
        this.anio_graduacion = anio_graduacion;
    }

    @Embeddable
    @AllArgsConstructor
    @NoArgsConstructor
    @EqualsAndHashCode
    private static class InscripcionKey implements Serializable {
        @Column(name = "id_estudiante")
        private int idEstudiante;
        @Column(name = "id_carrera")
        private int idCarrera;
    }

}