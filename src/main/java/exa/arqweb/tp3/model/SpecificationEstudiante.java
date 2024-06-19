package exa.arqweb.tp3.model;

import exa.arqweb.tp3.dto.EstudianteDTO;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

public class SpecificationEstudiante {

    public static Specification<EstudianteDTO> hasGenero(String genero) {
        return (root, query, builder) ->
            builder.equal(root.get("genero"), genero);
    }

    public static Specification<EstudianteDTO> hasCiudadResidencia(String ciudad) {
        return (root, query, builder) ->
                builder.equal(root.get("ciudad_residencia"), ciudad);
    }

    public static Specification<EstudianteDTO> hasAnioInscripcion(String anio_inscripcion) {
        return (root, query, builder) -> {
            var rootInscripcion = query.from(Inscripcion.class);
            Predicate anioInscripcion = builder.equal(rootInscripcion.get("anio_inscripcion"), anio_inscripcion);
            Predicate estudianteExistsInInscripcion = builder.equal(root.get("libreta_universitaria"), rootInscripcion.get("estudiante").get("libreta_universitaria"));
            return builder.and(anioInscripcion, estudianteExistsInInscripcion);
        };
    }

}
