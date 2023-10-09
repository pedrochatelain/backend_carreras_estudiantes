package exa.arqweb.tp3.exception;

import exa.arqweb.tp3.dto.ResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({CarreraAlreadyExists.class})
    public ResponseEntity<Object> handleStudentNotFoundException(CarreraAlreadyExists exception) {
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(new ResponseDTO(HttpStatus.CONFLICT.value(), exception.getMessage(), null));
    }

}