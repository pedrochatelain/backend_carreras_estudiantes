package exa.arqweb.tp3.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"carrera", "anio", "inscriptos", "egresados"})
@SuppressWarnings("unused")
public interface ReporteCarreraDTO {
    String getCarrera();
    Long getAnio();
    Long getInscriptos();
    Long getEgresados();
}