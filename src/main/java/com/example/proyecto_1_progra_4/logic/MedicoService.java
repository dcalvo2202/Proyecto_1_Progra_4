package com.example.proyecto_1_progra_4.logic;

import com.example.proyecto_1_progra_4.Medico;
import com.example.proyecto_1_progra_4.data.MedicoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MedicoService {
    private final MedicoRepository medicoRepository;

    public MedicoService(MedicoRepository medicoRepository) {
        this.medicoRepository = medicoRepository;
    }

    public List<Medico> obtenerMedicos() {
        return medicoRepository.findAll();
    }

    public Optional<Medico> obtenerMedicoPorId(Integer id) {
        return medicoRepository.findByMedicoId(id);
    }

    public Medico guardarMedico(Medico medico) {
        return medicoRepository.save(medico);
    }
}