package exa.arqweb.tp3.service;

import exa.arqweb.tp3.model.Usuario;
import exa.arqweb.tp3.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    private UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public Usuario getUser(String nameOfUser) {
        return usuarioRepository.findByName(nameOfUser);
    }

}
