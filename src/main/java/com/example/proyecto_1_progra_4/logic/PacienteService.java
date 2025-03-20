package com.example.proyecto_1_progra_4.logic;

import com.example.proyecto_1_progra_4.Paciente;
import com.example.proyecto_1_progra_4.data.PacienteRepository;
import org.springframework.stereotype.Service;

import java.awt.event.PaintEvent;
import java.util.List;
import java.util.Optional;

@Service
public class PacienteService {
    private final PacienteRepository pacienteRepository;

    public PacienteService(PacienteRepository pacienteRepository) {
        this.pacienteRepository = pacienteRepository;
    }

    public List<Paciente> obtenerPacientes() {
        return pacienteRepository.findAll();
    }

    public Optional<Paciente> obtenerPacientePorId(Integer id) {
        return pacienteRepository.findByPacienteId(id);
    }

    public Paciente guardarPaciente(Paciente paciente) {
        return pacienteRepository.save(paciente);
    }
}