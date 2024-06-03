package exa.arqweb.tp3.repository;

import exa.arqweb.tp3.model.Inscripcion;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
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

    @Modifying
    @Transactional
    @Query("""
        DELETE
        FROM Inscripcion i
        WHERE i.carrera.id = :idCarrera
    """)
    void deleteByCarreraId(long idCarrera);
}