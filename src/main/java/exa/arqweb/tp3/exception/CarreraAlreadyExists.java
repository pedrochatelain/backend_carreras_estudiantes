package exa.arqweb.tp3.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CarreraAlreadyExists extends RuntimeException {

    private int status_code;
    private String message;

}