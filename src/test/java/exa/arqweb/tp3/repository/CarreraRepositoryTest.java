package exa.arqweb.tp3.repository;

import exa.arqweb.tp3.model.Carrera;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@DataJpaTest
class CarreraRepositoryTest {

    @Autowired
    CarreraRepository carreraRepository;

    @Test
    void increment_size_of_list_when_adding_carrera () {
        carreraRepository.save(new Carrera("dwdw"));
        assertEquals(carreraRepository.findAll().size(), 1);
    }

    @Test
    void fetch_added_carrera() {
        carreraRepository.save(new Carrera("testCarrera"));
        Carrera carrera = carreraRepository.getCarrera("testCarrera");
        assertTrue(carrera.getNombre().equals("testCarrera"));
    }

    @Test
    void should_return_null_when_fetching_nonexistent_carrera() {
        Carrera carrera = carreraRepository.getCarrera("testCarrera");
//        assertTrue(carrera == null);
        assertTrue(carrera == null);
    }

}