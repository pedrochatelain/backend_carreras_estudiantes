package exa.arqweb.tp3.service;

import exa.arqweb.tp3.dto.CarreraDTO;
import exa.arqweb.tp3.dto.ResponseDTO;
import exa.arqweb.tp3.exception.CustomException;
import exa.arqweb.tp3.model.Carrera;
import exa.arqweb.tp3.repository.CarreraRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CarreraServiceTest {

    @Mock
    CarreraRepository carreraRepository;

    @InjectMocks
    CarreraService carreraService;

    @Test
    void existeCarrera_should_return_true() {
        Carrera carrera = new Carrera("carreraTest");
        when(carreraRepository.getCarrera(carrera.getNombre())).thenReturn(carrera);
        assertTrue(carreraService.existeCarrera(carrera.getNombre()));
    }

    @Test
    void existeCarrera_should_return_false() {
        Carrera carrera = new Carrera("carreraTest");
        when(carreraRepository.getCarrera(carrera.getNombre())).thenReturn(null);
        assertFalse(carreraService.existeCarrera(carrera.getNombre()));
    }

    @Test
    void save_carrera_returns_status_code_created() {
        CarreraDTO carreraDTO = new CarreraDTO();
        carreraDTO.setNombre("carreraTest");
        ResponseDTO response = carreraService.save(carreraDTO);
        assertEquals(response.getStatus_code(), HttpStatus.CREATED.value());
    }

    @Test
    void throw_exception_when_saving_carrera_that_already_exists() {
        CarreraDTO carreraDTO = new CarreraDTO();
        carreraDTO.setNombre("carreraTest");
        when(carreraRepository.getCarrera(anyString())).thenReturn(new Carrera());
        var error = assertThrows(CustomException.class, () -> {
            carreraService.save(carreraDTO);
        });
        assertTrue(error.getStatus_code() == HttpStatus.CONFLICT.value());
        assertTrue(error.getMessage().equals("Ya existe la carrera con el nombre: " + carreraDTO.getNombre()));
    }



}