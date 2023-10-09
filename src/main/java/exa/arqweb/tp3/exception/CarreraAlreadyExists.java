package exa.arqweb.tp3.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class CarreraAlreadyExists extends CustomException {

    private final static int status_code = HttpStatus.CONFLICT.value();
    private final static String message = "Ya existe la carrera con el nombre: ";

    public CarreraAlreadyExists(String nombre_carrera) {
        super(status_code, message + nombre_carrera);
    }

}