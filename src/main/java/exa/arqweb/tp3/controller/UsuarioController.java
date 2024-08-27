package exa.arqweb.tp3.controller;

import exa.arqweb.tp3.service.UsuarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UsuarioController {

    private UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping(value = "api/users/{name}")
    public ResponseEntity<?> getUser(@PathVariable("name") String username) {
        System.out.println("fuck");
        return ResponseEntity.status(HttpStatus.OK).body(usuarioService.getUser(username));
    }

}
