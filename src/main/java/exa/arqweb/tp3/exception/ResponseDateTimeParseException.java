package exa.arqweb.tp3.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ResponseDateTimeParseException {

    private String exception;
    private String help;

}
