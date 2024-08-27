package exa.arqweb.tp3.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class RequestEstudiantesDTO {

    private  String  nombre;
    private  String  genero;
    private Integer  anio_inscripcion;
    private  String  ciudad;
}
