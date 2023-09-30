package exa.arqweb.tp3.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CarreraDTO {

    private String nombre;

    public CarreraDTO() {}

    public CarreraDTO(String nombre) {
        this.nombre = nombre;
    }

}
