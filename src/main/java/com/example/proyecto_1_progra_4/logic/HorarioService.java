package com.example.proyecto_1_progra_4.logic;

import com.example.proyecto_1_progra_4.Horario;
import com.example.proyecto_1_progra_4.data.HorarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class HorarioService {
    @Autowired
    private HorarioRepository horarioRepository;

    public List<Horario> listarHorarios() {
        return horarioRepository.findAll();
    }

    public Optional<Horario> obtenerHorarioPorId(Integer id) {
        return horarioRepository.findById(id);
    }

   /* public List<Horario> obtenerHorariosPorMedico(Integer medicoId) {
        return horarioRepository.findByMedicoId(medicoId);
    } */

    public List<Horario> obtenerHorariosPorDiaSemana(String diaSemana) {
        return horarioRepository.findByDiaSemana(diaSemana);
    }

    public Horario guardarHorario(Horario horario) {
        return horarioRepository.save(horario);
    }

    public void eliminarHorario(Integer id) {
        horarioRepository.deleteById(id);
    }
}

  /*  public List<Horario> obtenerHorariosPorMedico(Integer medicoId) {

    } */

