package exa.arqweb.tp3.service;

import exa.arqweb.tp3.dto.EstudianteDTO;
import exa.arqweb.tp3.dto.RequestEstudiantesDTO;
import exa.arqweb.tp3.dto.ResponseDTO;
import exa.arqweb.tp3.model.Estudiante;
import exa.arqweb.tp3.repository.InscripcionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import exa.arqweb.tp3.repository.EstudianteRepository;

import java.util.List;

import static exa.arqweb.tp3.model.SpecificationEstudiante.*;

@Service
public class EstudianteService {

    private final EstudianteRepository estudianteRepository;
    private final InscripcionRepository inscripcionRepository;

    @Autowired
    public EstudianteService(EstudianteRepository estudianteRepository, InscripcionRepository inscripcionRepository) {
        this.estudianteRepository = estudianteRepository;
        this.inscripcionRepository = inscripcionRepository;
    }

    @Transactional
    public ResponseDTO guardarEstudiante(EstudianteDTO dto) {
        Estudiante estudiante = dto.toEstudiante();
        return new ResponseDTO(HttpStatus.CREATED.value(), "Se creó correctamente el estudiante", estudianteRepository.save(estudiante));
    }

    public List<EstudianteDTO> getEstudiantes(RequestEstudiantesDTO request) {
        var ciudad = request.getCiudad();
        var genero = request.getGenero();
        var anio_inscripcion = request.getAnio_inscripcion();
        var nombre = request.getNombre();
        Specification<EstudianteDTO> specification = Specification.where(null);
        if (ciudad != null) {
            specification = specification.and(hasCiudadResidencia(ciudad.toLowerCase()));
        }
        if (genero != null) {
            specification = specification.and(hasGenero(genero.toLowerCase()));
        }
        if (anio_inscripcion != null) {
            specification = specification.and(hasAnioInscripcion(anio_inscripcion));
        }
        if (nombre != null) {
            specification = specification.and(hasNombre(nombre.toLowerCase()));
        }
        return estudianteRepository.findAll(specification);
    }

    public ResponseDTO deleteEstudiante(long id) {
        if (estudianteRepository.existsById(id)) {
            inscripcionRepository.deleteByEstudianteId(id);
            estudianteRepository.deleteById(id);
            return new ResponseDTO(HttpStatus.OK.value(), "Se borró el estudiante correctamente");
        }
        return new ResponseDTO(HttpStatus.NOT_FOUND.value(), "El estudiante con libreta universitaria: " + id + " no existe");
    }
}