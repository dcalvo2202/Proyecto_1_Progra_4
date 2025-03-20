package com.example.proyecto_1_progra_4.logic;

import com.example.proyecto_1_progra_4.Citas;
import com.example.proyecto_1_progra_4.data.CitasRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CitasService {

    private final CitasRepository citasRepository;

    public CitasService(CitasRepository citasRepository) {
        this.citasRepository = citasRepository;
    }

    public Optional<Citas> obtenerCitaPorId(Integer id) {
        return citasRepository.findById(id);
    }

    public List<Citas> obtenerCitas() {
        return citasRepository.findAll();
    }

    public Citas guardarCita(Citas citas) {
        return citasRepository.save(citas);
    }
}