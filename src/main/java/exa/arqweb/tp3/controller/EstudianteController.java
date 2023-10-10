package exa.arqweb.tp3.controller;

import exa.arqweb.tp3.dto.EstudianteDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import exa.arqweb.tp3.service.EstudianteService;

import java.util.List;

@RestController
@RequestMapping(path = "api/estudiantes")
public class EstudianteController {

    private final EstudianteService estudianteService;

    @Autowired
    public EstudianteController(EstudianteService estudianteService) {
        this.estudianteService = estudianteService;
    }

    @PostMapping("")
    public ResponseEntity guardarEstudiante(@RequestBody @Valid EstudianteDTO estudianteDTO) {
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
    public ResponseEntity<List<EstudianteDTO>> getEstudiantes(String carrera, String ciudad) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(estudianteService.getEstudiantes(carrera, ciudad));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @GetMapping(params = {"sort"})
    public ResponseEntity getEstudiantes(String sort) {
        return ResponseEntity.status(HttpStatus.OK).body(estudianteService.getEstudiantes(sort));
    }

}