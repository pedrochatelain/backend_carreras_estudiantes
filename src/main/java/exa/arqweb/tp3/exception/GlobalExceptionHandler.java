package exa.arqweb.tp3.exception;

import exa.arqweb.tp3.dto.ResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({CarreraAlreadyExists.class})
    public ResponseEntity<Object> handleCarreraAlreadyExistsException(CarreraAlreadyExists exception) {
        return ResponseEntity
                .status(exception.getStatus_code())
                .body(new ResponseDTO(exception.getStatus_code(), exception.getMessage(), null));
    }

    @ExceptionHandler({EstudianteNotFound.class})
    public ResponseEntity<Object> handleStudentNotFoundException(EstudianteNotFound exception) {
        return ResponseEntity
                .status(exception.getStatus_code())
                .body(new ResponseDTO(exception.getStatus_code(), exception.getMessage(), null));
    }

    @ExceptionHandler({CarreraNotFound.class})
    public ResponseEntity<Object> handleCarreraNotFoundException(CarreraNotFound exception) {
        return ResponseEntity
                .status(exception.getStatus_code())
                .body(new ResponseDTO(exception.getStatus_code(), exception.getMessage(), null));
    }

    @ExceptionHandler({InscripcionAlreadyExists.class})
    public ResponseEntity<Object> handleInscripcionAlreadyExistsException(InscripcionAlreadyExists exception) {
        return ResponseEntity
                .status(exception.getStatus_code())
                .body(new ResponseDTO(exception.getStatus_code(), exception.getMessage(), null));
    }

}