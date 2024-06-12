package exa.arqweb.tp3.exception;

import exa.arqweb.tp3.dto.ResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import java.time.format.DateTimeParseException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({CustomException.class})
    public ResponseEntity<Object> handleException(CustomException exception) {
        return ResponseEntity
                .status(exception.getStatus_code())
                .body(new ResponseDTO(exception.getStatus_code(), exception.getMessage(), null));
    }

    @ExceptionHandler(value = DateTimeParseException.class)
    public ResponseEntity<?> handleException(DateTimeParseException e) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new ResponseDateTimeParseException(e.getLocalizedMessage(), "Las fechas deben cumplir con el formato dd-MM-yyyy. Por ejemplo, 5 de julio del año 1997 debe representarse así 05-07-1997"));
    }

}