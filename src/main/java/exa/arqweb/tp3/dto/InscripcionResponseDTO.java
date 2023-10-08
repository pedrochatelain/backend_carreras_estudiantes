package exa.arqweb.tp3.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class InscripcionResponseDTO {

    private int id_estudiante;
    private int id_carrera;
    private int status_code;
    private String response_message;

}