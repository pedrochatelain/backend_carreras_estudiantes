package exa.arqweb.tp3.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Setter
@Getter
public class InscripcionRequestDTO {

    @NotNull
    private Integer id_carrera;

    @NotNull
    private Integer id_estudiante;

    @NotNull
    private Integer anio_inscripcion;

}