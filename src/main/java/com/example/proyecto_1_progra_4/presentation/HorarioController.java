package com.example.proyecto_1_progra_4.presentation;

import com.example.proyecto_1_progra_4.logic.HorariosMedico;
import com.example.proyecto_1_progra_4.logic.Service;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/horarios_medicos")
public class HorarioController {

    @Autowired
    private Service service;

    @GetMapping
    public Iterable<HorariosMedico> listarHorarios() {
        return service.listarHorarios();
    }

    @GetMapping("/{id}")
    public Optional<HorariosMedico> obtenerHorario(@PathVariable Integer id) {
        return service.obtenerHorarioPorId(id);
    }


    @GetMapping("/dia/{diaSemana}")
    public List<HorariosMedico> obtenerHorariosPorDia(@PathVariable String diaSemana) {
        return service.obtenerHorariosPorDiaSemana(diaSemana);
    }

    @PostMapping
    public HorariosMedico crearHorario(@RequestBody HorariosMedico horario) {
        return service.guardarHorario(horario);
    }

    @DeleteMapping("/{id}")
    public void eliminarHorario(@PathVariable Integer id) {
        service.eliminarHorario(id);
    }
}