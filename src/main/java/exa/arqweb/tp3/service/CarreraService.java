package exa.arqweb.tp3.service;

import exa.arqweb.tp3.dto.CarreraDTO;
import exa.arqweb.tp3.dto.CarrerasConInscriptosDTO;
import exa.arqweb.tp3.dto.ReporteCarreraDTO;
import exa.arqweb.tp3.dto.ResponseDTO;
import exa.arqweb.tp3.exception.CustomException;
import exa.arqweb.tp3.model.Carrera;
import exa.arqweb.tp3.repository.CarreraRepository;
import exa.arqweb.tp3.repository.InscripcionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CarreraService {

    private final CarreraRepository carreraRepository;
    private final InscripcionRepository inscripcionRepository;

    @Autowired
    public CarreraService(CarreraRepository carreraRepository, InscripcionRepository inscripcionRepository) {
        this.carreraRepository = carreraRepository;
        this.inscripcionRepository = inscripcionRepository;
    }

    @Transactional
    public ResponseDTO save(CarreraDTO carreraDTO) {
        String nombre = carreraDTO.getNombre();
        if (existeCarrera(nombre))
            throw new CustomException(HttpStatus.CONFLICT.value(), "Ya existe la carrera con el nombre: " + nombre);
        Carrera carreraCreada = carreraRepository.save(new Carrera(nombre));
        return new ResponseDTO(HttpStatus.CREATED.value(), "Se ha creado correctamente la carrera", carreraCreada);
    }

    public boolean existeCarrera(String nombre) {
        return carreraRepository.getCarrera(nombre) != null;
    }

    @Transactional
    public List<ReporteCarreraDTO> getReporte() {
        return carreraRepository.getReporte();
    }

    @Transactional
    public List<CarrerasConInscriptosDTO> getCarreras(String sortValue) {
        if (sortValue.isBlank())
            throw new CustomException(HttpStatus.BAD_REQUEST.value(), "Debe especificar el ordenamiento: sort=cantidad-inscriptos");
        sortValue = sortValue.trim();
        if ( ! sortValue.equals("cantidad-inscriptos"))
            throw new CustomException(HttpStatus.BAD_REQUEST.value(), "ERROR: El atributo de ordenamiento `" + sortValue + "` no existe. Solo se puede ordenar por `cantidad-inscriptos`");

        return carreraRepository.getCarrerasConInscriptos();
    }

    public void deleteCarrera(long id) {
        inscripcionRepository.deleteByCarreraId(id);
        carreraRepository.deleteById(id);
    }
}