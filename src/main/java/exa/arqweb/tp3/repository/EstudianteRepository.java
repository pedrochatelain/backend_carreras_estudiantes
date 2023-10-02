package exa.arqweb.tp3.repository;

import exa.arqweb.tp3.dto.EstudianteDTO;
import exa.arqweb.tp3.model.Estudiante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EstudianteRepository extends JpaRepository<Estudiante, Long> {

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

}
