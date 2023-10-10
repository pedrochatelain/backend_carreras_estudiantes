package exa.arqweb.tp3.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class EstudianteAlreadyExists extends CustomException {

    private final static int status_code = HttpStatus.CONFLICT.value();
    private final static String message = "Ya existe el estudiante con el id: ";

    public EstudianteAlreadyExists(int id_estudiante) {
        super(status_code, message + id_estudiante);
    }

}