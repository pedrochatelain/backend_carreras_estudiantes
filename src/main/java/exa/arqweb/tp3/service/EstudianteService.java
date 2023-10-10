package exa.arqweb.tp3.service;

import exa.arqweb.tp3.dto.EstudianteDTO;
import exa.arqweb.tp3.dto.ResponseDTO;
import exa.arqweb.tp3.exception.CustomException;
import exa.arqweb.tp3.model.Estudiante;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    public ResponseDTO guardarEstudiante(EstudianteDTO estudianteDTO) {
        if (estudianteRepository.existsById((long) estudianteDTO.getLibreta_universitaria()))
            throw new CustomException(HttpStatus.CONFLICT.value(), "Ya existe el estudiante con id: " + estudianteDTO.getLibreta_universitaria());

        Estudiante estudiante = new Estudiante(
            estudianteDTO.getLibreta_universitaria(),
            estudianteDTO.getNombre(),
            estudianteDTO.getApellido(),
            estudianteDTO.getEdad(),
            estudianteDTO.getGenero(),
            estudianteDTO.getDni(),
            estudianteDTO.getCiudad_residencia()
        );
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

}