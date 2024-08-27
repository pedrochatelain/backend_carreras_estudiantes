package exa.arqweb.tp3.controller;

import exa.arqweb.tp3.dto.EstudianteDTO;
import exa.arqweb.tp3.dto.RequestEstudiantesDTO;
import exa.arqweb.tp3.dto.ResponseDTO;
import exa.arqweb.tp3.swagger.SwaggerEstudianteAPI;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import exa.arqweb.tp3.service.EstudianteService;

@RestController("api/estudiantes")
public class EstudianteController implements SwaggerEstudianteAPI {

    private final EstudianteService estudianteService;

    public EstudianteController(EstudianteService estudianteService) {
        this.estudianteService = estudianteService;
    }

    @PostMapping("api/estudiantes")
    public ResponseEntity<?> guardarEstudiante(@RequestBody EstudianteDTO estudianteDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(estudianteService.guardarEstudiante(estudianteDTO));
    }

    @GetMapping("api/estudiantes")
    public ResponseEntity<?> getEstudiantes(@ParameterObject RequestEstudiantesDTO request) {
        return ResponseEntity.status(HttpStatus.OK).body(estudianteService.getEstudiantes(request));
    }

    @DeleteMapping("api/estudiantes/{id}")
    public ResponseEntity<ResponseDTO> deleteEstudiante(@PathVariable("id") long id) {
        ResponseDTO response = estudianteService.deleteEstudiante(id);
        return ResponseEntity.status(response.getStatus_code()).body(response);
    }

}