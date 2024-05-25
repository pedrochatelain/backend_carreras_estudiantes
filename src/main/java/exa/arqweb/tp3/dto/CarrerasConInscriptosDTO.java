package exa.arqweb.tp3.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CarrerasConInscriptosDTO {

    private int id;
    private String carrera;
    private int inscriptos;

    public CarrerasConInscriptosDTO(int id, String carrera, Long inscriptos) {
        this.carrera = carrera;
        this.inscriptos = inscriptos.intValue();
        this.id = id;
    }

}