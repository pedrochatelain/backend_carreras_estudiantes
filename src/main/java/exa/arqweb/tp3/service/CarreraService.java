package exa.arqweb.tp3.service;

import exa.arqweb.tp3.dto.CarreraDTO;
import exa.arqweb.tp3.dto.CarrerasConInscriptosDTO;
import exa.arqweb.tp3.dto.ReporteCarreraDTO;
import exa.arqweb.tp3.dto.ResponseDTO;
import exa.arqweb.tp3.exception.CarreraAlreadyExists;
import exa.arqweb.tp3.exception.CustomException;
import exa.arqweb.tp3.model.Carrera;
import exa.arqweb.tp3.repository.CarreraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CarreraService {

    private final CarreraRepository carreraRepository;

    @Autowired
    public CarreraService(CarreraRepository carreraRepository) {
        this.carreraRepository = carreraRepository;
    }

    @Transactional
    public ResponseDTO save(CarreraDTO carreraDTO) {
        String nombre = carreraDTO.getNombre();
        if (existeCarrera(nombre))
            throw new CarreraAlreadyExists(carreraDTO.getNombre());
        Carrera carreraCreada = carreraRepository.save(new Carrera(nombre));
        return new ResponseDTO(HttpStatus.CREATED.value(), "Se ha creado correctamente la carrera", carreraCreada);
    }

    private boolean existeCarrera(String nombre) {
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

}