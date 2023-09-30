package exa.arqweb.tp3.service;

import exa.arqweb.tp3.dto.EstudianteDTO;
import exa.arqweb.tp3.model.Estudiante;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import exa.arqweb.tp3.repository.EstudianteRepository;

@Service
public class EstudianteService {

    private final EstudianteRepository estudianteRepository;

    @Autowired
    public EstudianteService(EstudianteRepository estudianteRepository) {
        this.estudianteRepository = estudianteRepository;
    }

    @Transactional
    public Estudiante matricularEstudiante(EstudianteDTO estudianteDTO) {
        Estudiante estudiante = new Estudiante(
            estudianteDTO.getLibreta_universitaria(),
            estudianteDTO.getNombre(),
            estudianteDTO.getApellido(),
            estudianteDTO.getEdad(),
            estudianteDTO.getGenero(),
            estudianteDTO.getDni(),
            estudianteDTO.getCiudad_residencia()
        );
        return estudianteRepository.save(estudiante);
    }

}
