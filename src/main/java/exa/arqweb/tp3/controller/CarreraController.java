package exa.arqweb.tp3.controller;

import exa.arqweb.tp3.dto.CarreraDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import exa.arqweb.tp3.service.CarreraService;

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
        System.out.println(carreraDTO);
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(carreraService.save(carreraDTO));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

}