package exa.arqweb.tp3.service;

import exa.arqweb.tp3.dto.InscripcionRequestDTO;
import exa.arqweb.tp3.dto.InscripcionResponseDTO;
import exa.arqweb.tp3.model.Carrera;
import exa.arqweb.tp3.model.Estudiante;
import exa.arqweb.tp3.model.Inscripcion;
import exa.arqweb.tp3.repository.CarreraRepository;
import exa.arqweb.tp3.repository.EstudianteRepository;
import exa.arqweb.tp3.repository.InscripcionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class InscripcionService {

    private final EstudianteRepository estudianteRepository;
    private final CarreraRepository carreraRepository;
    private final InscripcionRepository inscripcionRepository;

    @Autowired
    public InscripcionService(EstudianteRepository estudianteRepository, CarreraRepository carreraRepository, InscripcionRepository inscripcionRepository) {
        this.estudianteRepository = estudianteRepository;
        this.carreraRepository = carreraRepository;
        this.inscripcionRepository = inscripcionRepository;
    }

    @Transactional
    public InscripcionResponseDTO inscribirEstudiante(InscripcionRequestDTO req) {
        Estudiante estudiante = estudianteRepository.getReferenceById((long) req.getId_estudiante());
        Carrera carrera = carreraRepository.getReferenceById((long) req.getId_carrera());
        Inscripcion inscripcion = new Inscripcion(estudiante, carrera, req.getAnio_inscripcion());
        inscripcionRepository.save(inscripcion);
        System.out.println(inscripcion);
        return new InscripcionResponseDTO(req.getId_estudiante(), req.getId_carrera(), 201, "Se matriculó correctamente al estudiante");
    }

}