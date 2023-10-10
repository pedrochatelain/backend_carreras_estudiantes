package exa.arqweb.tp3.controller;

import exa.arqweb.tp3.dto.InscripcionRequestDTO;
import exa.arqweb.tp3.service.InscripcionService;
import jakarta.validation.Valid;
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
    public ResponseEntity inscribirEstudiante(@RequestBody @Valid InscripcionRequestDTO req) {
        return ResponseEntity.status(HttpStatus.CREATED).body(inscripcionService.inscribirEstudiante(req));
    }

}