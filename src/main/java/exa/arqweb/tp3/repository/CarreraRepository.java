package exa.arqweb.tp3.repository;

import exa.arqweb.tp3.dto.CarrerasConInscriptosDTO;
import exa.arqweb.tp3.dto.ReporteCarreraDTO;
import exa.arqweb.tp3.model.Carrera;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarreraRepository extends JpaRepository<Carrera, Long> {

    @Query(value = """
        SELECT nombre AS carrera, anio_graduacion AS anio, MAX(cant_inscriptos) AS inscriptos, MAX(cant_graduados) AS egresados
        FROM (
             SELECT c.nombre, anio_graduacion, COUNT(anio_graduacion) AS cant_graduados, 0 AS cant_inscriptos
             FROM carrera c LEFT JOIN inscripcion i ON i.id_carrera = c.id
             WHERE anio_graduacion IS NOT NULL
             GROUP BY c.id, anio_graduacion
             UNION (
                 SELECT c.nombre, anio_inscripcion, 0, COUNT(anio_inscripcion) AS cant_inscriptos
                 FROM carrera c LEFT JOIN inscripcion i ON i.id_carrera = c.id
                 GROUP BY c.id, anio_inscripcion
             )
        ) graduados_inscriptos
        GROUP BY nombre, anio
        ORDER BY nombre, anio
    """, nativeQuery = true)
    List<ReporteCarreraDTO> getReporte();

    @Query("""
        SELECT new exa.arqweb.tp3.dto.CarrerasConInscriptosDTO(c.nombre, COUNT(i.anio_inscripcion))
        FROM Carrera c LEFT JOIN Inscripcion i ON c.id = i.carrera.id
        GROUP BY c.id
        ORDER BY COUNT(i.anio_inscripcion)
    """)
    List<CarrerasConInscriptosDTO> getCarrerasConInscriptos();

}