package com.example.proyecto_1_progra_4.presentation;

import com.example.proyecto_1_progra_4.logic.HorariosMedico;
import com.example.proyecto_1_progra_4.service.Service;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

//    @PostMapping
//    public HorariosMedico crearHorario(@RequestBody HorariosMedico horario) {
//        return service.guardarHorario(horario);
//    }

    @PostMapping("/create")
    public ResponseEntity<?> crearHorario(@Valid @RequestBody HorariosMedico horario, BindingResult result) {
        if (result.hasErrors()) {
            List<String> errores = result.getAllErrors().stream().map(e -> e.getDefaultMessage()).collect(Collectors.toList());
            return ResponseEntity.badRequest().body(errores);
        }
        return ResponseEntity.ok(service.guardarHorario(horario));
    }

    @DeleteMapping("/delete/{id}")
    public void eliminarHorario(@PathVariable Integer id) {
        service.eliminarHorario(id);
    }
}
