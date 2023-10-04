package exa.arqweb.tp3.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CarrerasConInscriptosDTO {

    private String carrera;
    private int inscriptos;

    public CarrerasConInscriptosDTO(String carrera, Long inscriptos) {
        this.carrera = carrera;
        this.inscriptos = inscriptos.intValue();
    }

}