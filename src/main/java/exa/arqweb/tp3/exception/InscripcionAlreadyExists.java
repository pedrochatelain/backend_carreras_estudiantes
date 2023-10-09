package exa.arqweb.tp3.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class InscripcionAlreadyExists extends CustomException {

    private final static int status_code = HttpStatus.CONFLICT.value();
    private int id_estudiante;
    private int id_carrera;

    public InscripcionAlreadyExists(int idEstudiante, int idCarrera) {
        super(status_code, "El estudiante con id {" + idEstudiante + "} ya fue inscripto en la carrera con id {" + idCarrera + "}");
    }
}