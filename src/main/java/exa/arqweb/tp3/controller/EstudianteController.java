package exa.arqweb.tp3.controller;

import exa.arqweb.tp3.dto.EstudianteDTO;
import exa.arqweb.tp3.dto.ResponseDTO;
import exa.arqweb.tp3.model.Estudiante;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import exa.arqweb.tp3.service.EstudianteService;

@RestController
@Tag(name = "Estudiantes")
public class EstudianteController {

    private final EstudianteService estudianteService;

    @Autowired
    public EstudianteController(EstudianteService estudianteService) {
        this.estudianteService = estudianteService;
    }

    @PostMapping("")
    public ResponseEntity guardarEstudiante(@RequestBody EstudianteDTO estudianteDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(estudianteService.guardarEstudiante(estudianteDTO));
    }

    @GetMapping(params = {"genero"})
    public ResponseEntity getEstudiantesPorGenero(String genero) {
            return ResponseEntity.status(HttpStatus.OK).body(estudianteService.getEstudiantePorGenero(genero));
    }

    @GetMapping(params = {"libreta_universitaria"})
    public ResponseEntity getEstudiantesPorLU(int libreta_universitaria) {
        return ResponseEntity.status(HttpStatus.OK).body(estudianteService.getEstudiantePorLU(libreta_universitaria));
    }

    @GetMapping(params = {"carrera", "ciudad"})
    public ResponseEntity<?> getEstudiantesPorCarreraYCiudad(String carrera, String ciudad) {
        return ResponseEntity.status(HttpStatus.OK).body(estudianteService.getEstudiantesPorCarreraYCiudad(carrera, ciudad));
    }

    @GetMapping(value = "api/estudiantes", produces = "application/json")
    @Operation(summary = "Retorna todos los estudiantes")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = Estudiante.class)) }),
    })
    public ResponseEntity<?> getEstudiantes() {
        return ResponseEntity.status(HttpStatus.OK).body(estudianteService.getEstudiantes());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDTO> deleteEstudiante(@PathVariable("id") long id) {
        ResponseDTO response = estudianteService.deleteEstudiante(id);
        return ResponseEntity.status(response.getStatus_code()).body(response);
    }

}