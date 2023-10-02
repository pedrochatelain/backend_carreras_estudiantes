package exa.arqweb.tp3.controller;

import exa.arqweb.tp3.dto.EstudianteDTO;
import exa.arqweb.tp3.model.Estudiante;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import exa.arqweb.tp3.service.EstudianteService;

import java.net.http.HttpResponse;
import java.util.List;

@RestController
@RequestMapping(path = "api/estudiantes")
public class EstudianteController {

    private final EstudianteService estudianteService;

    @Autowired
    public EstudianteController(EstudianteService estudianteService) {
        this.estudianteService = estudianteService;
    }

    @GetMapping
    public String test() {
        return "estudiante controller works!";
    }

    @PostMapping("")
    public ResponseEntity<Estudiante> matricularEstudiante(@RequestBody EstudianteDTO estudianteDTO) {
            System.out.println(estudianteDTO);
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(estudianteService.matricularEstudiante(estudianteDTO));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @GetMapping(params = {"genero"})
    public ResponseEntity<List<EstudianteDTO>> getEstudiantesPorGenero(String genero) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(estudianteService.getEstudiantePorGenero(genero));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

}
