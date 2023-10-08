package exa.arqweb.tp3.controller;

import exa.arqweb.tp3.dto.InscripcionRequestDTO;
import exa.arqweb.tp3.dto.InscripcionResponseDTO;
import exa.arqweb.tp3.service.InscripcionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/inscripciones")
public class InscripcionController {

    private final InscripcionService inscripcionService;

    @Autowired
    public InscripcionController(InscripcionService inscripcionService) {
        this.inscripcionService = inscripcionService;
    }

    @PostMapping("")
    public ResponseEntity<InscripcionResponseDTO> inscribirEstudiante(@RequestBody InscripcionRequestDTO req) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(inscripcionService.inscribirEstudiante(req));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

}