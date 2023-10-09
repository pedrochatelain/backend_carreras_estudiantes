package exa.arqweb.tp3.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class CarreraNotFound extends CustomException {

    private final static int status_code = HttpStatus.NOT_FOUND.value();
    private final static String message = "No existe la carrera con id: ";

    public CarreraNotFound(int id_carrera) {
        super(status_code, message + id_carrera);
    }

}