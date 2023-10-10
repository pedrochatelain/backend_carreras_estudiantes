package exa.arqweb.tp3.exception;

import exa.arqweb.tp3.dto.ResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({CustomException.class})
    public ResponseEntity<Object> handleException(CustomException exception) {
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