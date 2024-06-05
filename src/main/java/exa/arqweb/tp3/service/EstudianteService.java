package exa.arqweb.tp3.service;

import exa.arqweb.tp3.dto.EstudianteDTO;
import exa.arqweb.tp3.dto.ResponseDTO;
import exa.arqweb.tp3.exception.CustomException;
import exa.arqweb.tp3.model.Estudiante;
import exa.arqweb.tp3.repository.InscripcionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import exa.arqweb.tp3.repository.EstudianteRepository;

import java.util.List;

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
        Estudiante estudiante = new Estudiante();

        estudiante.setNombre(dto.getNombre());
        estudiante.setApellido(dto.getApellido());
        estudiante.setEdad(dto.getEdad());
        estudiante.setGenero(dto.getGenero());
        estudiante.setDni(dto.getDni());
        estudiante.setCiudad_residencia(dto.getCiudad_residencia());

        return new ResponseDTO(HttpStatus.CREATED.value(), "Se creó correctamente el estudiante", estudianteRepository.save(estudiante));
    }

    @Transactional
    public List<EstudianteDTO> getEstudiantePorGenero(String genero) {
        genero = genero.trim();
        if (genero.isBlank())
            throw new CustomException(HttpStatus.BAD_REQUEST.value(), "Debe especificar un género");
        List<EstudianteDTO> estudiantes = estudianteRepository.getEstudiantePorGenero(genero);
        if (estudiantes.isEmpty())
            throw new CustomException(HttpStatus.NOT_FOUND.value(), "El género `" + genero + "` no existe o no ha sido encontrado");
        return estudiantes;
    }

    @Transactional
    public List<EstudianteDTO> getEstudiantePorLU(int lu) {
        if ( ! estudianteRepository.existsById((long) lu))
            throw new CustomException(HttpStatus.NOT_FOUND.value(), "El estudiante con lu `" + lu + "` no existe");
        return estudianteRepository.getEstudiantePorLU(lu);
    }

    @Transactional
    public List<EstudianteDTO> getEstudiantesPorCarreraYCiudad(String carrera, String ciudad) {
        if (carrera.isEmpty() || ciudad.isEmpty() || carrera.isBlank() || ciudad.isBlank())
            throw new CustomException(HttpStatus.BAD_REQUEST.value(), "Debe especificar obligtoriamente carrera y ciudad. Por ejemplo ciudad=tandil&carrera=TUDAI");
        carrera = carrera.trim();
        ciudad = ciudad.trim();
        return estudianteRepository.getEstudiantesPorCarreraYCiudad(carrera, ciudad);
    }

    @Transactional
    public List<EstudianteDTO> getEstudiantes(String sort) {
        String sortValue = sort.trim();
        if (sortValue.equals("apellido"))
            return estudianteRepository.getEstudiantesOrderByApellido();
        throw new CustomException(HttpStatus.BAD_REQUEST.value(), "ERROR: El atributo de ordenamiento `" + sortValue + "` no existe. Solo se puede ordenar por `apellido`");
    }

    public List<Estudiante> getEstudiantes() {
        return estudianteRepository.findAll();
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