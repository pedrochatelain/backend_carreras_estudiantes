package exa.arqweb.tp3.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseDTO {

    private int status_code;
    private String message;
    private Object entity;

    public ResponseDTO(int status_code, String message) {
        this.status_code = status_code;
        this.message = message;
    }
}