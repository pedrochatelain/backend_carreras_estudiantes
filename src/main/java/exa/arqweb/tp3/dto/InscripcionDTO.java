package exa.arqweb.tp3.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InscripcionDTO {

    private Integer id_estudiante;
    private Integer id_carrera;
    private Integer anio_inscripcion;
    private Integer anio_graduacion;

    public InscripcionDTO(int id_estudiante, int id_carrera, int anio_inscripcion) {
        this.id_estudiante = id_estudiante;
        this.id_carrera = id_carrera;
        this.anio_inscripcion = anio_inscripcion;
    }
}