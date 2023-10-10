package exa.arqweb.tp3.service;

import exa.arqweb.tp3.dto.EstudianteDTO;
import exa.arqweb.tp3.exception.EstudianteAlreadyExists;
import exa.arqweb.tp3.model.Estudiante;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import exa.arqweb.tp3.repository.EstudianteRepository;

import java.util.List;

@Service
public class EstudianteService {

    private final EstudianteRepository estudianteRepository;

    @Autowired
    public EstudianteService(EstudianteRepository estudianteRepository) {
        this.estudianteRepository = estudianteRepository;
    }

    @Transactional
    public Estudiante guardarEstudiante(EstudianteDTO estudianteDTO) {
        if (estudianteRepository.existsById((long) estudianteDTO.getLibreta_universitaria()))
            throw new EstudianteAlreadyExists(estudianteDTO.getLibreta_universitaria());

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

    @Transactional
    public List<EstudianteDTO> getEstudiantePorGenero(String genero) {
        return estudianteRepository.getEstudiantePorGenero(genero);
    }

    @Transactional
    public List<EstudianteDTO> getEstudiantePorLU(int lu) {
        return estudianteRepository.getEstudiantePorLU(lu);
    }

    @Transactional
    public List<EstudianteDTO> getEstudiantes(String carrera, String ciudad) {
        return estudianteRepository.getEstudiantes(carrera, ciudad);
    }

    @Transactional
    public List<EstudianteDTO> getEstudiantes(String sort) throws Exception {
        if (sort.equals("apellido"))
            return estudianteRepository.getEstudiantesOrderByApellido();
        throw new Exception();
    }

}