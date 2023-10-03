package exa.arqweb.tp3.repository;

import exa.arqweb.tp3.dto.ReporteCarreraDTO;
import exa.arqweb.tp3.model.Carrera;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarreraRepository extends JpaRepository<Carrera, Long> {

    @Query(name = "get_reporte_carreras", nativeQuery = true)
    List<ReporteCarreraDTO> getReporte();

}