package exa.arqweb.tp3.repository;

import exa.arqweb.tp3.dto.EstudianteDTO;
import exa.arqweb.tp3.model.Estudiante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EstudianteRepository extends JpaRepository<Estudiante, Long>, JpaSpecificationExecutor<EstudianteDTO> {

    @Query("""
        SELECT new exa.arqweb.tp3.dto.EstudianteDTO(e.libreta_universitaria, e.nombre, e.apellido, e.edad, e.dni, e.ciudad_residencia, e.genero)
        FROM Estudiante e
        WHERE e.genero = :genero
    """)
    List<EstudianteDTO> getEstudiantePorGenero(String genero);

    @Query("""
        SELECT new exa.arqweb.tp3.dto.EstudianteDTO(e.libreta_universitaria, e.nombre, e.apellido, e.edad, e.dni, e.ciudad_residencia, e.genero)
        FROM Estudiante e
        WHERE e.libreta_universitaria = :lu
    """)
    List<EstudianteDTO> getEstudiantePorLU(int lu);

    @Query("""
        SELECT new exa.arqweb.tp3.dto.EstudianteDTO(e.libreta_universitaria, e.nombre, e.apellido, e.edad, e.dni, e.ciudad_residencia, e.genero)
        FROM Estudiante e JOIN Inscripcion i ON e.libreta_universitaria = i.estudiante.libreta_universitaria JOIN Carrera c ON c.id = i.carrera.id
        WHERE c.nombre LIKE :carrera AND e.ciudad_residencia LIKE :ciudad
    """)
    List<EstudianteDTO> getEstudiantesPorCarreraYCiudad(String carrera, String ciudad);

    @Query("""
        SELECT new exa.arqweb.tp3.dto.EstudianteDTO(e.libreta_universitaria, e.nombre, e.apellido, e.edad, e.dni, e.ciudad_residencia, e.genero)
        FROM Estudiante e
        ORDER BY e.apellido
    """)
    List<EstudianteDTO> getEstudiantesOrderByApellido();

}