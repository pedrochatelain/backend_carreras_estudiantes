package exa.arqweb.tp3.swagger;

import exa.arqweb.tp3.dto.EstudianteDTO;
import exa.arqweb.tp3.dto.RequestEstudiantesDTO;
import exa.arqweb.tp3.dto.ResponseDTO;
import exa.arqweb.tp3.model.Estudiante;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;

@Tag(name = "Estudiantes")
@SecurityRequirement(name = "bearerAuth")
public interface SwaggerEstudianteAPI {

    @ApiResponses({
        @ApiResponse( responseCode = "200", content = { @Content(schema = @Schema(implementation = ResponseDTO.class)) }),
    })
    ResponseEntity<?> guardarEstudiante(EstudianteDTO estudianteDTO);

    @ApiResponses({
        @ApiResponse( responseCode = "200", content = { @Content(array = @ArraySchema(schema = @Schema(implementation = Estudiante.class))) }),
    })
    ResponseEntity<?> getEstudiantes(RequestEstudiantesDTO requestEstudiantesDTO);

}