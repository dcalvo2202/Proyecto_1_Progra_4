package com.example.proyecto_1_progra_4.presentation;

import com.example.proyecto_1_progra_4.Horario;
import com.example.proyecto_1_progra_4.logic.HorarioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/horarios")
public class HorarioController {

    @Autowired
    private HorarioService horarioService;

    @GetMapping
    public List<Horario> listarHorarios() {
        return horarioService.listarHorarios();
    }

    @GetMapping("/{id}")
    public Optional<Horario> obtenerHorario(@PathVariable Integer id) {
        return horarioService.obtenerHorarioPorId(id);
    }


    @GetMapping("/dia/{diaSemana}")
    public List<Horario> obtenerHorariosPorDia(@PathVariable String diaSemana) {
        return horarioService.obtenerHorariosPorDiaSemana(diaSemana);
    }

    @PostMapping
    public Horario crearHorario(@RequestBody Horario horario) {
        return horarioService.guardarHorario(horario);
    }

    @DeleteMapping("/{id}")
    public void eliminarHorario(@PathVariable Integer id) {
        horarioService.eliminarHorario(id);
    }
}

/*   @GetMapping("/medico/{medicoId}")
public List<Horario> obtenerHorariosPorMedico(@PathVariable Integer medicoId) {
    return horarioService.obtenerHorariosPorMedico(medicoId);
} */