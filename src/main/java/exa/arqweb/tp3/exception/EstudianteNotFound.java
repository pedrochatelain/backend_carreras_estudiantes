package exa.arqweb.tp3.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class EstudianteNotFound extends CustomException {

    private final static int status_code = HttpStatus.NOT_FOUND.value();
    private final static String message = "No existe el estudiante con id: ";

    public EstudianteNotFound(int id_estudiante) {
        super(status_code, message + id_estudiante);
    }

}