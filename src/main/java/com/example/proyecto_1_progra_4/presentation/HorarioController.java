package com.example.proyecto_1_progra_4.presentation;

import com.example.proyecto_1_progra_4.logic.Horario;
import com.example.proyecto_1_progra_4.logic.Service;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/horarios")
public class HorarioController {

    @Autowired
    private Service service;

    @GetMapping
    public Iterable<Horario> listarHorarios() {
        return service.listarHorarios();
    }

    @GetMapping("/{id}")
    public Optional<Horario> obtenerHorario(@PathVariable Integer id) {
        return service.obtenerHorarioPorId(id);
    }


    @GetMapping("/dia/{diaSemana}")
    public List<Horario> obtenerHorariosPorDia(@PathVariable String diaSemana) {
        return service.obtenerHorariosPorDiaSemana(diaSemana);
    }

    @PostMapping
    public Horario crearHorario(@RequestBody Horario horario) {
        return service.guardarHorario(horario);
    }

    @DeleteMapping("/{id}")
    public void eliminarHorario(@PathVariable Integer id) {
        service.eliminarHorario(id);
    }
}

/*   @GetMapping("/medico/{medicoId}")
public List<Horario> obtenerHorariosPorMedico(@PathVariable Integer medicoId) {
    return horarioService.obtenerHorariosPorMedico(medicoId);
} */