package exa.arqweb.tp3.controller;

import exa.arqweb.tp3.dto.CarreraDTO;
import exa.arqweb.tp3.dto.CarrerasConInscriptosDTO;
import exa.arqweb.tp3.dto.ReporteCarreraDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import exa.arqweb.tp3.service.CarreraService;

import java.util.List;

@RestController
@RequestMapping(path = "api/carreras")
public class CarreraController {

    private final CarreraService carreraService;

    @Autowired
    public CarreraController(CarreraService carreraService) {
        this.carreraService = carreraService;
    }

    @GetMapping
    public String test() {
        return "carreras controller works!";
    }

    @PostMapping("")
    public ResponseEntity<CarreraDTO> saveCarrera(@RequestBody CarreraDTO carreraDTO) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(carreraService.save(carreraDTO));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @GetMapping(path = "reporte")
    public ResponseEntity<List<ReporteCarreraDTO>> getReporte() {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(carreraService.getReporte());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @GetMapping(params = {"sort"})
    public ResponseEntity<List<CarrerasConInscriptosDTO>> getCarreras(String sort) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(carreraService.getCarreras(sort));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

}