package exa.arqweb.tp3.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CarreraDTO {

    private String nombre;

    @SuppressWarnings("unused")
    public CarreraDTO() {
        // debe existir el constructor vacío para poder
        // devolver CarreraDTO como ResponseEntity
        // en CarreraController
    }

    public CarreraDTO(String nombre) {
        this.nombre = nombre;
    }

}
