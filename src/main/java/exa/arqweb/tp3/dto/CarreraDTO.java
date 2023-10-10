package exa.arqweb.tp3.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CarreraDTO {

    @NotNull
    private String nombre;

}