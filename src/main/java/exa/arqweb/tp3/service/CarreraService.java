package exa.arqweb.tp3.service;

import exa.arqweb.tp3.dto.CarreraDTO;
import exa.arqweb.tp3.model.Carrera;
import exa.arqweb.tp3.repository.CarreraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CarreraService {

    private final CarreraRepository carreraRepository;

    @Autowired
    public CarreraService(CarreraRepository carreraRepository) {
        this.carreraRepository = carreraRepository;
    }

    @Transactional
    public CarreraDTO save(CarreraDTO carreraDTO) {
        Carrera carrera = new Carrera(carreraDTO.getNombre());
        Carrera carreraCreada = carreraRepository.save(carrera);
        return new CarreraDTO(carreraCreada.getNombre());
    }

}
