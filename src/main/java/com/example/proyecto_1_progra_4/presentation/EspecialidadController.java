package com.example.proyecto_1_progra_4.presentation;

import com.example.proyecto_1_progra_4.logic.Especialidad;
import com.example.proyecto_1_progra_4.logic.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/especialidades")
public class EspecialidadController {

    @Autowired
    private Service service;

    @GetMapping
    public Iterable<Especialidad> listarEspecialidades() {
        return service.listarEspecialidades();
    }
    @GetMapping("/{id}")
    public Optional<Especialidad> obtenerEspecialidad(@PathVariable Integer id) {
        return service.obtenerEspecialidadPorId(id);
    }

    @PostMapping
    public Especialidad crearEspecialidad(@RequestBody Especialidad especialidad) {
        return service.guardarEspecialidad(especialidad);
    }
}

