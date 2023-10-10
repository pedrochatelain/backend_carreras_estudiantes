package exa.arqweb.tp3.controller;

import exa.arqweb.tp3.dto.EstudianteDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import exa.arqweb.tp3.service.EstudianteService;

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
    public ResponseEntity<?> getEstudiantesPorCarreraYCiudad(String carrera, String ciudad) {
        return ResponseEntity.status(HttpStatus.OK).body(estudianteService.getEstudiantesPorCarreraYCiudad(carrera, ciudad));
    }

    @GetMapping(params = {"sort"})
    public ResponseEntity getEstudiantes(String sort) {
        return ResponseEntity.status(HttpStatus.OK).body(estudianteService.getEstudiantes(sort));
    }

}