package exa.arqweb.tp3.repository;

import exa.arqweb.tp3.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    @Query("""
        SELECT u
        FROM Usuario u
        WHERE u.nombre = :nombre
    """)
    Usuario findByName(String nombre);

}
