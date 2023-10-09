package exa.arqweb.tp3.repository;

import exa.arqweb.tp3.model.Inscripcion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface InscripcionRepository extends JpaRepository<Inscripcion, Long> {

    @Query("""
        SELECT 1
        FROM Inscripcion i
        WHERE i.carrera.id = :idCarrera AND i.estudiante.libreta_universitaria = :idEstudiante
    """)
    Integer getInscripcion(int idEstudiante, int idCarrera);
}