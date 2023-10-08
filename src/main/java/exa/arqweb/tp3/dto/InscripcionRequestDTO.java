package exa.arqweb.tp3.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class InscripcionRequestDTO {

    private int id_carrera;
    private int id_estudiante;
    private int anio_inscripcion;

}