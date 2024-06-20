package exa.arqweb.tp3.controller;

import exa.arqweb.tp3.dto.EstudianteDTO;
import exa.arqweb.tp3.dto.ResponseDTO;
import exa.arqweb.tp3.model.Estudiante;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
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

    @PostMapping(value = "api/estudiantes", produces = "application/json")
    public ResponseEntity guardarEstudiante(@RequestBody EstudianteDTO estudianteDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(estudianteService.guardarEstudiante(estudianteDTO));
    }

    @ApiResponses({@ApiResponse(responseCode = "200", content = { @Content(array = @ArraySchema(schema = @Schema(implementation = Estudiante.class))) }),})
    @GetMapping(value = "api/estudiantes", produces = "application/json")
    public ResponseEntity<?> getEstudiantes(
    @RequestParam(required = false) String genero,
    @RequestParam(required = false) String nombre,
    @RequestParam(required = false) Integer anio_inscripcion,
    @RequestParam(required = false) String ciudad  ) {
        return ResponseEntity.status(HttpStatus.OK).body(estudianteService.getEstudiantes(genero, nombre, anio_inscripcion, ciudad));
    }

    @DeleteMapping(value = "api/estudiantes/{id}", produces = "application/json")
    public ResponseEntity<ResponseDTO> deleteEstudiante(@PathVariable("id") long id) {
        ResponseDTO response = estudianteService.deleteEstudiante(id);
        return ResponseEntity.status(response.getStatus_code()).body(response);
    }

}