package exa.arqweb.tp3.service;

import exa.arqweb.tp3.dto.InscripcionDTO;
import exa.arqweb.tp3.dto.InscripcionRequestDTO;
import exa.arqweb.tp3.dto.ResponseDTO;
import exa.arqweb.tp3.exception.CustomException;
import exa.arqweb.tp3.exception.EstudianteNotFound;
import exa.arqweb.tp3.exception.InscripcionAlreadyExists;
import exa.arqweb.tp3.model.Carrera;
import exa.arqweb.tp3.model.Estudiante;
import exa.arqweb.tp3.model.Inscripcion;
import exa.arqweb.tp3.repository.CarreraRepository;
import exa.arqweb.tp3.repository.EstudianteRepository;
import exa.arqweb.tp3.repository.InscripcionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    public ResponseDTO inscribirEstudiante(InscripcionRequestDTO req) {
        checkRequest(req);
        Estudiante estudiante = estudianteRepository.getReferenceById((long) req.getId_estudiante());
        Carrera carrera = carreraRepository.getReferenceById((long) req.getId_carrera());
        Inscripcion inscripcion = new Inscripcion(estudiante, carrera, req.getAnio_inscripcion());
        inscripcionRepository.save(inscripcion);
        return new ResponseDTO(HttpStatus.CREATED.value(), "Se matriculó correctamente al estudiante", new InscripcionDTO(req.getId_estudiante(), req.getId_carrera(), req.getAnio_inscripcion()));
    }

    // Corrobora que exista el estudiante, la carrera y que la inscripción no haya sido realizada anteriormente
    private void checkRequest(InscripcionRequestDTO req) {
        if ( ! estudianteRepository.existsById((long) req.getId_estudiante()) )
            throw new EstudianteNotFound(req.getId_estudiante());
        if ( ! carreraRepository.existsById((long) req.getId_carrera()) )
            throw new CustomException(HttpStatus.NOT_FOUND.value(), "No existe la carrera con id: " + req.getId_carrera());
        if ( inscripcionRepository.getInscripcion(req.getId_estudiante(), req.getId_carrera()) != null)
            throw new InscripcionAlreadyExists(req.getId_estudiante(), req.getId_carrera());
    }

}